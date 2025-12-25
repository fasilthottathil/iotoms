# Cart Shell & Drawer

**Story ID**: CART-0001
**Priority**: Critical
**Status**: Completed

## Requirement

Build the cart workspace hosting drawer navigation, transaction controls, and a responsive layout for quick picks + product grid. The shell must respect portrait/landscape orientation and provide friendly entry points for future cart logic.

**User Value**: Cashiers land on a familiar POS layout ready for transactions.
**Business Impact**: Establishes the base for incremental cart features (payments, discounts, etc.).

## Design Context

- Top app bar with menu icon, RETURN/HOLD/CUSTOMER/CLEAR actions.
- Modal drawer presenting POS utilities through `cartDrawerItem`.
- Main content splits into quick pick row + product grid (portrait) or multi-column layout (landscape).
- Material 3 styling with brand colors from theme tokens.

**Design Assets**:
- Compose components in `ui/components`.
- Colors/dimensions from `ui/theme`.

## User Flow

### Entry Points
- Navigating to Cart from the main nav.

### Flow Steps
1. App bar renders with icons and actions sized to `ButtonHeight`.
2. Tapping the dashboard icon opens the `ModalNavigationDrawer`.
3. Drawer lists quick links and “Configurations” row.
4. Content area shows quick picks + product grid (or future calculator).

### Exit Points
- Drawer closes via UI gestures.
- Action buttons trigger placeholder callbacks, to be wired to cart logic.

## Acceptance Criteria

1. **Orientation-aware Layout**:
   - Portrait uses `CartScreenCompact` with quick pick carousel and `LazyVerticalGrid`.
   - Landscape uses `CartScreenExpanded` (mirrors same toggles with extra columns).
2. **Drawer**:
   - Uses `rememberDrawerState` and opens/closes via icon tap.
   - `cartDrawerItem()` renders entries vertically.
3. **Top App Bar Actions**:
   - RETURN/HOLD/CUSTOMER/CLEAR rows render text + icons.
   - Buttons respond to taps (even if callbacks are placeholder).
4. **Quick Pick/Product UI**:
   - `QuickPickItem` displayed in `LazyRow`.
   - Product grid uses adaptive cell width (200dp min).
5. **State Persistence**:
   - `canShowGeneralCalculator` boolean persists on configuration change (handled via `rememberSaveable`).

## Testing Notes

### Test Scenarios
1. **Happy Path**: Toggle drawer, scroll quick picks/grid, no crashes.
2. **Edge Cases**:
   - Rotate device; confirm layout swap.
   - Drawer open during rotation remains open.
3. **Error Handling**:
   - None yet; ensure placeholders don't block UI.

### Manual Test Checklist
- [ ] Drawer opens/closes smoothly.
- [ ] App bar icons respond with ripple feedback.
- [ ] Quick pick row scrolls horizontally.
- [ ] Product grid respects spacing.

## Implementation Notes

### Technical Approach
- Compose `ModalNavigationDrawer` + `Scaffold` host the structure.
- App bar actions implemented via `Row` with icons + text.
- Orientation checked with `getDeviceOrientation()`.

### File Structure
```
app/src/main/java/com/iotoms/ui/cart/
├── CartScreen.kt
├── CartScreenCompact.kt
├── CartScreenExpanded.kt
├── GeneralItemCalculatorScreen.kt
└── ...
app/src/main/java/com/iotoms/ui/components/
├── CartDrawerItem.kt
├── ProductItem.kt
├── QuickPickItem.kt
└── CartItem.kt
```

### Key Components
- **CartScreen**: Entry point with drawer + state.
- **CartScreenCompact/Expanded**: Layout logic for content area.
- **cartDrawerItem**: Drawer list content.

### Dependencies
- Compose Material 3, theme tokens, `getDeviceOrientation`.

### Implementation Checklist
- [x] Drawer + app bar wiring.
- [x] Quick pick + product grid layout.
- [x] Responsive state handling.

## Dependencies

### Blocked By
- None.

### Blocks
- CART-0002 (calculator toggle), future discount/payment stories.

### Related Stories
- CART-0002 shares toggle state with this shell.

## Notes
- Replace placeholder items with real data once cart repository is wired.
