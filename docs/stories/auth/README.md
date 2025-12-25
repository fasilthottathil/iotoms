# Authentication

## Overview
This feature area covers how registers and employees authenticate into the iotoms kiosk. It includes the merchant login experience (domain + register credentials), the on-shift employee login options (PIN and password), and the forgot-password handoff.

## Business Context
Stores must secure registers while still letting employees sign in quickly during a shift. These flows establish the kiosk session, store bearer tokens, and expose the UI entry points employees need to access their workloads.

## User Value
- Merchants can activate a register with their tenant information.
- Employees can unlock the kiosk with familiar PIN/password tools.
- Password resets happen without leaving the kiosk.

## Stories Overview

| Story ID | Title | Priority | Status | Description |
|----------|-------|----------|--------|-------------|
| REG-0001 | Merchant Login Screen | Critical | Completed | Multi-field login shell wired to RegisterUseCase and Ktor auth |
| REG-0002 | Employee PIN Login UI | High | Completed | Numeric keypad workflow for staff PIN unlock on both orientations |
| REG-0003 | Employee Password Login UI | High | Completed | Username/password entry with PIN toggle and forgot-password affordance |
| REG-0004 | Forgot Password Screen | Medium | Completed | Username capture and CTA to hand off to reset channel |

## User Flows
- [Auth Entry Flow](./user-flows/auth-entry-flow.md): Covers merchant login, employee PIN/password, and the forgot-password branch.

## Implementation Status
- [x] Story REG-0001 - Merchant Login Screen
- [x] Story REG-0002 - Employee PIN Login UI
- [x] Story REG-0003 - Employee Password Login UI
- [x] Story REG-0004 - Forgot Password Screen

## Technical Overview

### Components
- **LoginScreen**: Switches between compact/expanded layouts, binds to `LoginViewModel`, and routes state.
- **EmployeePinLoginScreen**: Orientation-aware PIN keypad with secure display toggle.
- **EmployeePasswordLoginScreen**: Username/password form with CTA buttons.
- **ForgotPasswordScreen**: Username capture + CTA and nav back to login.
- **LoaderDialog**: Shared overlay used by login.

### Domain & Data
- **RegisterUseCase** (`domain/usecase/auth`) drives the merchant login call.
- **AuthenticationRepositoryImpl** uses Ktor to call `/register` and persists tokens with `AppPreference`.
- **ApiClient** injects bearer headers + tenant metadata based on stored values.

### State Management
- **LoginViewModel** (`ui/auth/login`) exposes `StateFlow<LoginUiState>` with Idle/Loading/Success/Error.
- Validation errors leverage `FormError` map per field for inline hints.

## Dependencies
- Koin modules: `viewModelModule`, `useCaseModule`, `repositoryModule`, `networkModule`, `storageModule`.
- Ktor client for API calls, Room/Prefs for persistence, Navigation 3 for route keys (`Login`).

## Future Enhancements
- Hook employee PIN/password screens into appropriate backend APIs.
- Add telemetry for failed logins and reset attempts.
- Integrate navigation to the authenticated home screen when login succeeds.
