# Data Sync Modal & Worker

**Story ID**: SYNC-0001  
**Priority**: Critical  
**Status**: Completed

## Requirement

After a merchant successfully logs in, the kiosk must immediately kick off a blocking data synchronization step that seeds the Room cache with catalog, customer, tax, pay-mode, register, and branding data. The UI should surface a non-dismissible modal with progress feedback while a foreground WorkManager job makes the network calls and persists results. Only after the sync succeeds should navigation advance to the cart shell. The job also has to satisfy Android 14 foreground-service restrictions by declaring the proper permissions, notification channel, and Worker factory wiring.

**User Value**: Cashiers only reach the cart once the kiosk is hydrated with the latest catalog and configuration data, so every button they touch works offline.  
**Business Impact**: Eliminates “empty cart” states on first boot and keeps the kiosk compliant with Android’s foreground-service policies while syncing potentially large datasets.

## Design Context

- `DataSyncDialogScreen` renders a modal `Dialog` with a Material 3 top app bar (“Data Sync”), rotating sync icon, progress bar, and helper text.
- Spinner uses a slow `rememberInfiniteTransition` rotation to emphasize long-running work; progress is visualized with `LinearProgressIndicator`.
- `DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)` prevent users from bypassing synchronization.
- Colors come from the existing theme; padding relies on `MediumPadding` tokens.

**Design Assets**:
- Compose-only components; no separate Figma yet—use current implementation for reference.

## User Flow

### Entry Points
- Automatic after `REG-0001` merchant login success (`onLogin` pushes the `DataSync` destination).
- Future manual entry point (e.g., “Re-sync data”) may reuse this screen, but today it is login-only.

### Flow Steps
1. Navigation pushes the `DataSync` entry. `DataSyncViewModel` is resolved via Koin and immediately calls `doSync()`, resetting `DataSyncUiState` to `Idle`.
2. `doSync()` builds a `OneTimeWorkRequest<DataSyncWorker>` with `BackoffPolicy.EXPONENTIAL` (30s) and enqueues it via `enqueueUniqueWork(name = DATA_SYNC_WORK, policy = ExistingWorkPolicy.REPLACE)`.
3. WorkManager spins up `DataSyncWorker`, which switches to foreground service mode, showing a notification on channel `sync`.
4. `DataSyncWorker` invokes `DataSyncUseCase`, collecting the `Flow<Triple<SyncStatus, Float, Boolean>>` emitted by `DataSyncRepositoryImpl`. Each triple represents dataset progress (items, customers, attributes, stores, venues, registers, branding, pay modes, taxes, users).
5. Worker uses `setProgress()` so `DataSyncViewModel` can monitor `WorkManager.getWorkInfosForUniqueWorkFlow()` and update `DataSyncUiState.Progress(progress)` in the dialog.
6. When `SyncStatus.COMPLETED` arrives, worker returns `Result.success()`. ViewModel emits `DataSyncUiState.SyncSuccess`, triggers `onDismiss()`, and the navigation stack adds `Cart`. On failure, worker reports `Result.failure()` and future iterations can surface a dedicated error UI.

### Exit Points
- Success: Dialog closes and navigation advances to `Cart`.
- Failure: Worker reports failure; today the dialog simply remains (future story will add error/retry affordances).
- Cancel/back: Disabled via `DialogProperties`; only system-level force stop can interrupt.

## Acceptance Criteria

1. **Blocking Modal UI**:
   - Compose dialog uses `DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)`.
   - Top app bar title “Data Sync”, rotating `Icons.Default.Sync`, `LinearProgressIndicator`, and helper text “Data synchronization is in progress…”.
   - Progress bar reflects `DataSyncUiState.Progress.progress` clamped to 0–10.
2. **Navigation Handshake**:
   - `LoginScreen` `onLogin` pops `Login` from the Navigation 3 back stack and pushes `DataSync`.
   - `DataSyncDialogScreen` only calls `onSync(true)` (which pushes `Cart`) when `DataSyncUiState.SyncSuccess` fires.
3. **WorkManager Integration**:
   - `DataSyncViewModel` enqueues a unique `DATA_SYNC_WORK` job with `ExistingWorkPolicy.REPLACE` and `BackoffPolicy.EXPONENTIAL` (30 seconds).
   - `workerModule` registers `DataSyncWorker` via the Koin WorkManager DSL; `App` configures `workManagerFactory()` so workers receive dependencies.
4. **Foreground Service Compliance**:
   - `App` creates notification channel ID `sync` (“Data Sync”) at startup.
   - `AndroidManifest` declares `android.permission.FOREGROUND_SERVICE` and `android.permission.FOREGROUND_SERVICE_DATA_SYNC`, and merges `androidx.work.impl.foreground.SystemForegroundService` with `android:foregroundServiceType="dataSync"`.
   - `DataSyncWorker` calls `setForeground(ForegroundInfo(SYNC_NOTIFICATION_ID, notification, FOREGROUND_SERVICE_TYPE_DATA_SYNC))`.
5. **Repository Coverage & Persistence**:
   - `DataSyncRepositoryImpl` sequentially invokes:
     - `ItemRepository.getAllItemsPaginated()` (fetches every catalog page and upserts via `ItemDao`).
     - `CustomerRepository.getCustomers()` (pages CRM contacts).
     - `AttributeRepository` for sizes, brands, colors, styles, categories, subcategories, departments.
     - `BusinessRepository.getAllStores()`, `.getAllVenues()`, `.getAllRegisters()`, `.getAllBranding()` persisting to their DAOs.
     - `PayModeRepository.getPayModes()`, `TaxRepository.getAllTaxes()`, `UserRepository.getAllUsers()`.
   - Each call emits a `Triple(SyncStatus.IN_PROGRESS, progressFloat, isSuccess)` before the final `SyncStatus.COMPLETED` triple (`progress = 10f`).
6. **Room Schema Extensions**:
   - `AppDatabase` now includes `PlanEntity`, `StoreEntity`, `VenueEntity`, `RegisterEntity`, `BrandingEntity`, plus DAO counterparts with `insert/delete` helpers.
   - `Converters` serialize new DTOs (e.g., `VenueResponseItem`, `StoreResponse`, `FeaturesItem`, `ImageGallery`) so entities can store nested payloads.
7. **UI State Management**:
   - `DataSyncUiState` sealed class exposes `Idle`, `Loading`, `Progress(progress: Float)`, `SyncSuccess`.
   - `DataSyncViewModel` updates a `StateFlow<DataSyncUiState>` (using `stateIn`) as WorkManager reports progress.
8. **API Constants & DI Wiring**:
   - `ApiUrl` declares `/merchant/plans`, `/catalog/business/venues|stores|registers|branding`, and other endpoints consumed during sync.
   - `repositoryModule` binds `AccountRepositoryImpl`, `BusinessRepositoryImpl`, and `DataSyncRepositoryImpl`; `useCaseModule` includes `DataSyncUseCase` (and `GetAllPlansUseCase` for future sync steps).

## Testing Notes

### Test Scenarios
1. **Happy Path**:
   - Log in with valid credentials to trigger the modal.
   - Confirm dialog cannot be dismissed, notification channel “Data Sync” appears, progress moves from ~0.1 to 10, and Cart renders afterward with populated data.
2. **Edge Cases**:
   - Relaunch the app with stored domain/token: ensure login and sync are skipped by starting directly at Cart (clear preferences to re-test).
   - Force-stop the app mid-sync; upon relaunch, the worker should restart thanks to `ExistingWorkPolicy.REPLACE`.
   - Rotate the device while the dialog is visible; UI must keep animating and show the correct progress value.
3. **Error Handling**:
   - Simulate network failure (turn off connectivity) so one of the repository calls fails—worker should report failure and logs should reflect `SyncStatus.FAILED`. (User-visible retry UX is deferred; verify diagnostics only.)

### Manual Test Checklist
- [ ] Notification tray shows “Sync in progress” while the worker runs and disappears afterward.
- [ ] Dialog ignores back presses and outside taps.
- [ ] Room tables (`items`, `customers`, `stores`, `venues`, `registers`, `branding`, etc.) contain fresh rows after the sync.
- [ ] After domain/token are saved, cold start bypasses the Data Sync screen entirely.

## Implementation Notes

### Technical Approach
- Compose dialog collects `State<DataSyncUiState>` and reacts to success/progress.
- `DataSyncViewModel` launches a coroutine in `init` to call `doSync()` and collect `WorkManager` updates via `getWorkInfosForUniqueWorkFlow(DataSyncWorker.WORK_NAME)`.
- WorkManager request uses `OneTimeWorkRequestBuilder<DataSyncWorker>()` with exponential backoff; `workerModule` and `KoinWorkerFactory` handle DI.
- `DataSyncWorker` wraps the `DataSyncUseCase` flow, translating `SyncStatus` into WorkManager progress, and returns `Result.success()` / `Result.failure()`.
- Room DAOs/entities map API payloads via mapper extensions (`toVenueEntity()`, `toStoreEntity()`, etc.).
- Navigation is defined in `IotomsNavigation`: `Login → DataSync → Cart`.

### File Structure
```
app/src/main/java/com/iotoms/ui/sync/
├── DataSyncDialogScreen.kt
├── DataSyncUiState.kt
└── DataSyncViewModel.kt
app/src/main/java/com/iotoms/data/worker/DataSyncWorker.kt
app/src/main/java/com/iotoms/domain/usecase/sync/DataSyncUseCase.kt
app/src/main/java/com/iotoms/domain/repository/DataSyncRepository.kt
app/src/main/java/com/iotoms/data/repository/DataSyncRepositoryImpl.kt
app/src/main/java/com/iotoms/data/repository/BusinessRepositoryImpl.kt
app/src/main/java/com/iotoms/data/repository/AccountRepositoryImpl.kt
app/src/main/java/com/iotoms/data/local/dao/{PlanDao,StoreDao,VenueDao,BrandingDao,RegisterDao}.kt
app/src/main/java/com/iotoms/data/local/entity/{PlanEntity,StoreEntity,VenueEntity,BrandingEntity,RegisterEntity}.kt
app/src/main/java/com/iotoms/data/local/db/{AppDatabase.kt,Converters.kt}
app/src/main/java/com/iotoms/data/enum/SyncStatus.kt
app/src/main/java/com/iotoms/ui/navigation/IotomsNavigation.kt
app/src/main/java/com/iotoms/App.kt
app/src/main/AndroidManifest.xml
app/src/main/res/drawable/ic_sync.xml
```

### Key Components
- **DataSyncDialogScreen**: Blocking UI that reacts to `DataSyncUiState`.
- **DataSyncViewModel**: Bridges WorkManager progress into Compose state.
- **DataSyncWorker**: Foreground worker that runs the sync pipeline.
- **DataSyncRepositoryImpl**: Coordinates repository calls and emits progress triples.
- **BusinessRepositoryImpl / AccountRepositoryImpl**: Persist business metadata (stores, venues, registers, branding, plans) into Room.
- **App / WorkManager DI**: Configure notification channel and worker factory.

### Dependencies
- AndroidX WorkManager + `androidx.work:work-runtime-ktx`.
- Koin 4 (view model + worker DSL).
- HttpClient/Ktor for API calls.
- Room (DAOs/entities) and Paging (downstream Cart story consumes the cached data).
- Compose Material 3 for the modal UI.

### Implementation Checklist
- [x] Create Room entities/DAOs/mappers for plans, venues, stores, registers, and branding.
- [x] Implement `BusinessRepositoryImpl` and `AccountRepositoryImpl` to map API responses into Room.
- [x] Wire `DataSyncRepositoryImpl`, `DataSyncUseCase`, and `DataSyncWorker` with WorkManager + Koin.
- [x] Build `DataSyncViewModel` + `DataSyncUiState` and hook them into `IotomsNavigation`.
- [x] Design the Compose modal with rotation animation and progress indicator.
- [x] Register foreground-service permissions, notification channel, and worker factory in `App` + `AndroidManifest`.

## Dependencies

### Blocked By
- REG-0001 merchant login must succeed to trigger the sync.

### Blocks
- CART-0001 and any cart/customer stories rely on this sync to populate Room before rendering.
- Future offline flows assume these datasets exist.

### Related Stories
- CART-0001 uses the paged items produced by this sync.
- REG-0001 hands navigation control to this modal.

## Notes
- There is no dedicated error UI yet; WorkManager failures are logged, and QA can re-trigger sync by re-running the login flow.
- To test fresh sync runs, clear app data or delete the stored domain/token so login is shown again.
