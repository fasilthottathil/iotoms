# Forgot Password Screen

**Story ID**: REG-0004
**Priority**: Medium
**Status**: Completed

## Requirement

Enable users who forgot their credentials to initiate a reset without leaving the kiosk. The screen collects a username (or email) and directs the user back to login after submitting the reset request.

**User Value**: Users can recover access independently.
**Business Impact**: Decreases support escalations and downtime.

## Design Context

- Mirrors branding from other auth screens (gradient header + logo).
- Form limited to a single username field, reset CTA, and “Back to Login” button.
- Scrollable layout to handle smaller portrait screens.

**Design Assets**:
- Compose forms built with shared `OutlinedTextBox` and `FilledButton`.

## User Flow

### Entry Points
- “Forgot password?” link on Employee Password login.
- Potential link from merchant login (future).

### Flow Steps
1. Screen renders according to orientation.
2. User types username.
3. User taps “Reset Password”; placeholder callback handles request.
4. User taps “Back to Login” to return.

### Exit Points
- Success: Reset request acknowledged (backend wiring pending) and user navigates back to login.
- Cancel: Immediate back navigation.
- Error: Once backend returns errors we’ll show inline copy; currently none.

## Acceptance Criteria

1. **Form Input**: Username field captures text and supports IME Next.
2. **Actions**:
   - Reset button triggers `onResetClick`.
   - Back to Login text button triggers `onLoginClick`.
3. **Brand Consistency**: Header gradient + logo match other auth screens.
4. **Responsiveness**: Layout scrolls to avoid IME overlap on compact devices.

## Testing Notes

### Test Scenarios
1. **Happy Path**: Enter username, tap reset, confirm callback invoked.
2. **Edge Cases**:
   - Field empty when reset tapped – ensure API call handles validation (future). Currently no guard.
   - Orientation change retains username text.

### Manual Test Checklist
- [ ] Header renders correctly.
- [ ] Buttons respond and show ripple.
- [ ] Transition back to login works.

## Implementation Notes

### Technical Approach
- Reuses structural pattern from other auth screens for continuity.
- Hooks will connect to password reset API via future use case.

### File Structure
```
app/src/main/java/com/iotoms/ui/auth/reset/
├── ForgotPasswordScreen.kt
├── ForgotPasswordScreenCompact.kt
└── ForgotPasswordScreenExpanded.kt
```

### Key Components
- **ForgotPasswordScreen**: Owns state and decides layout.
- **Compact/Expanded**: Compose forms sized for portrait/landscape.

### Dependencies
- Shared theme + button components.

### Implementation Checklist
- [x] Username capture with state persistence.
- [x] Reset + back CTA callbacks.
- [x] Scroll + orientation handling.

## Dependencies

### Blocked By
- None.

### Blocks
- REG-0008: Password reset API integration.

### Related Stories
- REG-0003 triggers this flow.

## Notes
- Add inline validation copy once backend contract defined.
