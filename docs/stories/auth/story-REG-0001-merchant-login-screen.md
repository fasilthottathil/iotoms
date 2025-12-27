# Merchant Login Screen

**Story ID**: REG-0001
**Priority**: Critical
**Status**: Completed

## Requirement

Provide the primary login experience for merchants registering a kiosk. The flow must capture username, password, tenant domain, and register ID, validate inputs, and trigger the backend register API so the kiosk stores bearer tokens for future calls. On success the UI must transition into the Data Sync modal before revealing Cart, and when a kiosk has an existing domain/token the navigation stack should bypass this story entirely and land on Cart automatically.

**User Value**: Merchants can activate a register from the kiosk without external tooling.
**Business Impact**: Establishes the secure session required to reach all other POS flows.

## Design Context

- Compose-only layouts with compact (portrait) and expanded (landscape) variants.
- Loader overlay when `LoginUiState` is `Loading`.
- Error messaging inline under the impacted field plus a general error banner.
- Responsive layout uses height heuristics to position artwork and the form.

**Design Assets**:
- Figma exploration: TBD (current implementation references Native design system colors and typography).
- Compose previews can be added to `LoginScreenCompact/Expanded` for validation.

## User Flow

### Entry Points
- App launch when no bearer token/domain is saved (default start destination).
- Kiosk boots with stored domain/token: navigation bypasses this screen and drops users directly into `Cart` (clear preferences to re-enter the flow).
- Post-logout/timeout events route back to `Login`.

### Flow Steps
1. Display `LoginScreen` (compact or expanded based on `DeviceOrientation`).
   - User action: Type username/password/domain/register ID.
   - System response: Local state updates via `rememberSaveable`.
   - UI state: Idle.
2. User taps `Sign In`.
   - User action: Submit form.
   - System response: `LoginViewModel.login()` validates required fields and password length, ensures register ID numeric.
   - UI state: Loader dialog while `LoginUiState.Loading`.
3. API call finishes.
   - System response: On success, tokens saved via `AppPreference` (domain + token) and `LoginUiState.LoginSuccess` emitted; on error, `LoginUiState.Error` with message/FormError.
   - UI state: Error copy displayed or, on success, loader dismissed.
4. Navigation handoff.
   - System response: `onLogin` removes the `Login` entry from the Navigation 3 back stack and pushes the `DataSync` destination so the sync dialog can enqueue WorkManager.
   - UI state: Data Sync modal appears; upon completion the stack advances to `Cart`.

### Exit Points
- Success: `Login` entry pops, `DataSync` dialog opens, and once sync completes the stack advances to `Cart`.
- Cancel: Back button closes the app (handled by OS).
- Error: Remain on login with errors until resolved.

## Acceptance Criteria

1. **Form Fidelity**: Username, password, domain, and register ID inputs persist across orientation changes using `rememberSaveable`.
2. **Validation**:
   - Username/password/domain cannot be empty.
   - Password requires ≥5 characters (mirrors `LoginViewModel` logic).
   - Register ID accepts digits only; numeric check performed before API call.
3. **Submit Behavior**:
   - Tapping `Sign In` triggers `LoginViewModel.login()` and shows `LoaderDialog` while `LoginUiState` is `Loading`.
   - Submit button clears prior error text upon subsequent edits.
4. **API Integration**:
   - Calls `RegisterUseCase` → `AuthenticationRepositoryImpl` → `client.post(ApiUrl.REGISTER)`.
   - Stores bearer token and tenant on success.
5. **Error Handling**:
   - Display `FormError` messages near offending fields.
   - Display generic error text when backend returns `ApiError`.
6. **Post-login Navigation**:
   - When `LoginUiState.LoginSuccess` is emitted, `onLogin` must remove the `Login` nav entry and push `DataSync`, ensuring the sync modal appears before `Cart`.
   - `DataSync` completion pushes `Cart`, so there is no direct jump from login to cart without sync.
7. **Auto Start Destination**:
   - `IotomsNavigation` injects `AppPreference` and sets the start destination to `Cart` whenever `getDomainName()` returns a non-empty value.
   - QA should clear stored preferences to re-enter the login flow; otherwise the app launches directly into Cart.

## Testing Notes

### Test Scenarios
1. **Happy Path**:
   - Fill valid data and submit.
   - Verify loader appears, tokens persist via `AppPreference`, navigation advances to `DataSync`, and Cart shows once sync completes.
2. **Edge Cases**:
   - Empty fields trigger inline errors.
   - Enter alpha characters in register ID; ensure they are rejected/ignored.
   - Rotate device mid-entry; fields retain values.
   - Relaunch app with stored domain/token and confirm login screen is skipped (clear prefs afterward for regression testing).
3. **Error Handling**:
   - Force API failure (mock `Result.Error`) and confirm error text displayed.
   - Simulate invalid credentials to ensure `FormError` map highlights username/password.

### Manual Test Checklist
- [ ] Portrait and landscape layouts render expected arrangement.
- [ ] Loader dialog appears/disappears correctly.
- [ ] Error dismisses upon field edits.
- [ ] Tokens saved in `AppPreference` once login succeeds.

## Implementation Notes

### Technical Approach
- Compose screens (compact/expanded) manage only UI state; business logic lives in `LoginViewModel`.
- ViewModel uses `MutableStateFlow` + `stateIn` to expose `LoginUiState`.
- Domain/data pipeline reuses shared `Result` type to emit success/error.
- `IotomsNavigation` injects `AppPreference` to decide whether `Login` or `Cart` is the start destination and, upon `LoginUiState.LoginSuccess`, pops `Login` and pushes the `DataSync` screen before routing to `Cart`.

### File Structure
```
app/src/main/java/com/iotoms/ui/auth/login/
├── LoginScreen.kt
├── LoginScreenCompact.kt
├── LoginScreenExpanded.kt
├── LoginUiState.kt
└── LoginViewModel.kt
app/src/main/java/com/iotoms/domain/usecase/auth/RegisterUseCase.kt
app/src/main/java/com/iotoms/data/repository/AuthenticationRepositoryImpl.kt
app/src/main/java/com/iotoms/data/local/pref/AppPreference.kt
```

### Key Components
- **LoginScreen**: Chooses layout, owns form state, invokes callback.
- **LoginViewModel**: Validates, triggers `RegisterUseCase`, handles UI state transitions.
- **RegisterUseCase**: Pass-through use case hitting repository.
- **AuthenticationRepositoryImpl**: Calls Ktor endpoint and persists tokens/domain.

### Dependencies
- `networkModule`, `storageModule`, `repositoryModule`, `useCaseModule`, `viewModelModule`.
- Ktor HTTP client + OkHttp engine, `AppPreference` for tenant info.

### Implementation Checklist
- [x] Compose layouts for portrait/landscape.
- [x] Validation logic inside `LoginViewModel`.
- [x] API call and preference persistence.
- [x] Loader + error UI states.
- [x] Tests/manual verification per above.

## Dependencies

### Blocked By
- None.

### Blocks
- REG-0005: Authenticated navigation shell (future).

### Related Stories
- REG-0002/0003/0004 share the same entry surface.
- SYNC-0001 consumes `onLogin` and handles the mandatory post-login sync handshake before allowing Cart.

## Notes
- The immediate post-login experience is the Data Sync modal followed by Cart; future home shell work can replace that handoff once implemented.
