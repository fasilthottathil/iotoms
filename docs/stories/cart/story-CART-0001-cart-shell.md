# Cart Shell & Drawer

**Story ID**: CART-0001
**Priority**: Critical
**Status**: Completed

## Requirement

Keep the cart shell responsive shell but wire it to real catalog data sourced from the Room cache. The screen must collect `LazyPagingItems<ItemEntity>` from a ViewModel, surface loading indicators while paging fetches data, and render the populated grid/cards across portrait and landscape modes without regressing the drawer or calculator toggle affordances.

**User Value**: Cashiers land on a populated POS surface where product cards reflect the synced catalog and react instantly to drawer/calculator interactions.
**Business Impact**: Ensures kiosks are transaction-ready after sync, proving that the offline cache + paging flow stays healthy before we layer more cart logic.

## Design Context

- Drawer/app bar visuals remain unchanged, but the product grid now shows live catalog entries with real price + ID copy and a Coil-backed placeholder image.
- While catalog data loads, a `CircularProgressIndicator` appears within the grid body instead of empty placeholder rows.
- Quick pick row and calculator layout continue to share Material 3 tokens so the paging swap is visually seamless.

**Design Assets**:
- Compose components from `ui/components`.
- Theme tokens in `ui/theme` (`ButtonHeight`, `SmallPadding`, etc.).

## User Flow

### Entry Points
- `Cart` destination in `IotomsNavigation` after login→sync or automatically when a stored domain/token exists.

### Flow Steps
1. Navigation pushes the `Cart` entry. `CartViewModel` is injected via Koin and exposes `uiState` plus `pagingItemsFlow`.
   - System: `CartScreen` collects `pagingItemsFlow` as `LazyPagingItems<ItemEntity>` alongside `State<CartUiState>`.
   - UI: Drawer/app bar render immediately; grid shows a spinner while paging refresh is loading.
2. Once paging completes, `LazyVerticalGrid` renders each `ItemEntity` via `ProductItem`, including Coil images, rupee pricing, and IDs.
3. Cashier may toggle the general calculator via the dialpad icon. `rememberSaveable` preserves the toggle while paging list state is paused.
4. Drawer interactions (quick links + configuration row) work in parallel with the data-backed grid.

### Exit Points
- Back actions leave the module (shared with root nav).
- Drawer actions still stub real navigation.
- Future add-to-cart actions will piggyback on the populated list.

## Acceptance Criteria

1. **Navigation & ViewModel wiring**:
   - `CartScreen` signature accepts `State<CartUiState>` and `LazyPagingItems<ItemEntity>`.
   - Koin’s `viewModelModule` provides `CartViewModel`, and navigation collects flows with `collectAsStateWithLifecycle` / `collectAsLazyPagingItems()`.
2. **Paging grid**:
   - Compact and expanded layouts both consume the same `LazyPagingItems` from `GetPaginatedItemsFromLocalUseCase`.
   - Paging uses `LazyVerticalGrid(GridCells.Adaptive(minSize = 200.dp))` keyed by `ItemEntity.id`.
3. **Loading state**:
   - When `pagingItems.loadState.refresh` is `LoadState.Loading`, show a centered `CircularProgressIndicator` inside the grid body.
   - Grid repaints automatically as Room emits updates; no manual state resets required after rotations.
4. **Product card data**:
   - `ProductItem` binds to `ItemEntity`, loading imagery via `rememberAsyncImagePainter` with `product_placeholder.xml` fallback.
   - Price text uses `item.sellingPrice.currencyFormat()` (₹ prefix) and IDs show `item.itemId`.
5. **Calculator toggle parity**:
   - Dialpad icon toggles `canShowGeneralCalculator` in both orientations; when true the calculator replaces the grid without losing paging state.

## Testing Notes

### Test Scenarios
1. **Happy Path**: Run data sync to seed Room, open Cart, and verify products populate via paging. Scroll, rotate, and reopen calculator to ensure list resumes.
2. **Edge Cases**:
   - Empty DB → spinner persists without crashes until sync finishes.
   - Insert/delete Room items (via dev tooling) and confirm list updates in place.
   - Rapid calculator toggles leave paging list intact when returning.
3. **Error Handling**:
   - Paging load failures (simulate by clearing DB mid-scroll) should stay stable; errors may surface in logging but UI should not crash.

### Manual Test Checklist
- [ ] Drawer/app bar actions remain responsive while the paged list is active.
- [ ] Product cards show name, ₹ price, and item ID with placeholder imagery.
- [ ] Loading indicator appears exactly during refresh and hides afterward.
- [ ] Landscape layout mirrors the same paged content plus cart column.

## Implementation Notes

### Technical Approach
- `CartViewModel` hosts `_uiState` (`CartUiState`) and `pagingItemsFlow = GetPaginatedItemsFromLocalUseCase().cachedIn(viewModelScope)`.
- The use case delegates to `ItemRepository.getPaginateItemsFromLocal()` which returns a `Flow<PagingData<ItemEntity>>`.
- `ItemRepositoryImpl` builds a Room-backed `Pager(PagingConfig(pageSize = 20, enablePlaceholders = false))` using `ItemDao.getPaginatedItems()`.
- `ProductItem` switched to Coil 3 compose with `product_placeholder.xml` plus the `currencyFormat()` extension for rupee formatting.
- `IotomsNavigation` injects the view model via Koin and feeds both `State<CartUiState>` and `LazyPagingItems` into `CartScreen`.

### File Structure
```
app/src/main/java/com/iotoms/ui/cart/
├── CartScreen.kt
├── CartScreenCompact.kt
├── CartScreenExpanded.kt
├── CartUiState.kt
├── CartViewModel.kt
├── GeneralItemCalculatorScreen.kt
app/src/main/java/com/iotoms/domain/usecase/item/GetPaginatedItemsFromLocalUseCase.kt
app/src/main/java/com/iotoms/data/repository/ItemRepositoryImpl.kt
app/src/main/java/com/iotoms/data/local/dao/ItemDao.kt
app/src/main/java/com/iotoms/ui/components/ProductItem.kt
app/src/main/java/com/iotoms/utils/extensions/TextFormatter.kt
```

### Key Components
- **CartScreen**: Drawer/app bar host; now consumes paged data and passes it to orientation layouts.
- **CartScreenCompact / Expanded**: Render quick picks, calculator toggle, and paged grid with loading fallback.
- **CartViewModel**: Exposes UI state + paging flow scoped to the nav destination.
- **ItemRepositoryImpl / ItemDao**: Persist catalog rows and expose paging sources for offline-first reads.

### Dependencies
- Compose Material 3 + adaptive layout tokens.
- `androidx.paging:paging-compose` and Room paging artifacts.
- Coil Compose (`io.coil-kt.coil3:coil-compose`) for product images.
- Theme + utility extensions (currency formatter).

### Implementation Checklist
- [x] Inject `CartViewModel` via Koin navigation entry.
- [x] Feed `LazyPagingItems<ItemEntity>` into both compact and expanded layouts.
- [x] Show progress indicator tied to paging load state.
- [x] Update `ProductItem` with Coil placeholder and rupee formatting.
- [x] Preserve calculator toggle + drawer parity with previous UX.

## Dependencies

### Blocked By
- SYNC-0001 – Data sync worker must populate `ItemEntity` rows before paging has content on a fresh install.

### Blocks
- CART-0002 – Shares toggle state, and future add-to-cart logic will consume the paged items.
- Future pricing/discount cart stories rely on the populated shell.

### Related Stories
- CART-0002 general calculator continues to live inside this shell.

## Notes
- Cart relies solely on the Room cache; ensure the sync worker finishes or seed data when testing.
- Quick picks remain placeholder chips until catalog tagging is delivered in a later iteration.
