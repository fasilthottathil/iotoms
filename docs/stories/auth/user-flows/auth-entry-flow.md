# Auth Entry Flow - User Flow

## Overview
Describes how the kiosk transitions from launch to an authenticated state across merchant login, employee PIN/password entry, and the forgot-password help path.

## Entry Points
1. **Cold Start / App Launch**: `MainActivity` immediately routes to `Login` if no bearer token exists.
2. **Session Timeout**: A future worker will log users out and push them back to `LoginScreen`.
3. **Shift Change**: Employees select PIN or password login from the kiosk menu.

## Flow Diagram

```
[App Launch] → [LoginScreen] → {Submit Merchant Credentials?}
                               ├─ Yes → [LoginViewModel.validate] → [RegisterUseCase] 
                               │          ├─ Success → [Persist tokens] → [Navigate to Home]
                               │          └─ Error   → [Inline FormError] → [Stay on Login]
                               └─ Employee Access?
                                     ├─ PIN → [EmployeePinLoginScreen] → [Submit PIN] → [Next API TBD]
                                     └─ Password → [EmployeePasswordLoginScreen] → [Forgot Password?]
                                                                       └─ [ForgotPasswordScreen] → [Reset CTA] → [Back to Login]
```

## Detailed Steps

### Step 1: Launch Merchant Login
**Screen**: `LoginScreen`
**User Action**: Enter username, password, tenant domain, register ID.
**System Response**: `LoginViewModel.login()` validates data, emits `LoginUiState`.
**UI Elements**: Multi-field form, error helper text, CTA button, loader dialog.
**Data Flow**: Uses `RegisterUseCase` → `AuthenticationRepositoryImpl` → `ApiClient`.
**Next Step**: → Step 2.

### Step 2: Merchant Login Result
**Success**: Persist bearer token + tenant in `AppPreference`, transition to next feature (future home nav).
**Error**: Show inline `FormError` entries or banner message, remain on login.

### Step 3: Employee PIN Login
**Screen**: `EmployeePinLoginScreen`
**User Action**: Tap digits on keypad, optionally toggle PIN visibility, submit or switch to password login.
**System Response**: UI updates local state only (backend integration pending).
**UI Elements**: Logo header, read-only PIN textbox, keypad, CTA row, link to password login.
**Data Flow**: Local `rememberSaveable` state until API integration is added.
**Next Step**: Submit PIN or jump to password login.

### Step 4: Employee Password Login
**Screen**: `EmployeePasswordLoginScreen`
**User Action**: Provide username/password, optionally toggle visibility, pick PIN login if desired.
**System Response**: Local state only for now; eventually calls employee auth API.
**UI Elements**: Logo header, fields, CTA buttons, inline "Forgot password?" link.

### Step 5: Forgot Password
**Screen**: `ForgotPasswordScreen`
**User Action**: Type username and tap Reset.
**System Response**: Local state with future integration to password reset API.
**UI Elements**: Username field, reset CTA, back-to-login button.
**Next Step**: Return to login or handle reset instructions.

## Success Scenarios
1. **Merchant Login Success**: Valid credentials update preferences and proceed to the first secured route.
2. **Employee PIN Entry**: User enters digits, toggles visibility, and posts a placeholder success event (to be wired).
3. **Password Reset**: User requests a reset and returns to login.

## Error Scenarios
1. **Form Validation**: Missing username/password/domain/register ID surfaces inline errors.
2. **API Failure**: `LoginViewModel` emits `LoginUiState.Error` with message from `ApiError`.
3. **Invalid PIN**: Future error state placeholder, currently handled locally by clearing digits.

## Exit Points
1. **Success**: Merchant login leads to the home destination; employee flows will route to POS screens after integration.
2. **Cancel**: Users can back out of PIN/password/forgot screens to return to login.
3. **Error**: Login stays onscreen with at least one actionable error message and allows resubmission.

## State Diagram (Optional)
- Idle → Loading → (Success | Error) for merchant login.
- Employee flows loop between local `rememberSaveable` state and navigation to other auth variants.
