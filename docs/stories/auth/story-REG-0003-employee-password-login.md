# Employee Password Login UI

**Story ID**: REG-0003
**Priority**: High
**Status**: Completed

## Requirement

Provide an alternative employee authentication flow using username/password credentials. The screen must include brand header, credential fields, password visibility toggle, login CTA, PIN-login shortcut, and a forgot-password affordance.

**User Value**: Employees without PINs can still gain access quickly.
**Business Impact**: Reduces support requests when PIN memory fails or security policy changes.

## Design Context

- Shares the same hero/logo treatment as the PIN screen.
- Two vertically stacked `OutlinedTextBox` fields with Material 3 styling.
- Inline text button to trigger forgot password.
- Secondary action linking back to the PIN keypad.

**Design Assets**:
- Color + typography from `ui/theme`.
- Buttons from `ui/components`.

## User Flow

### Entry Points
- From PIN screen (“Login With Password”).
- Direct entry via kiosk nav.

### Flow Steps
1. Orientation-specific layout displays username + password fields.
2. User types text; password field supports show/hide.
3. User taps Login (future API call) or PIN Login button.
4. Optional “Forgot password?” text opens the reset screen.

### Exit Points
- Success: Routes to home (pending backend story).
- Cancel: Back button or PIN login action returns to previous view.
- Error: Inline errors will be added once backend validation returns messages.

## Acceptance Criteria

1. **Form Inputs**:
   - Username and password fields capture state via `rememberSaveable`.
   - Password field offers visibility toggle.
2. **Navigation Hooks**:
   - “Forgot password?” launches reset screen callback.
   - “Pin Login” action triggers `onPinLoginClick`.
3. **Login CTA**:
   - Tapping Login fires `onLoginClick`.
   - Button disabled state can be wired later; currently always enabled for mock data entry.
4. **Accessibility**:
   - Buttons sized with `MediumPadding` spacing and contain descriptive text/icons.

## Testing Notes

### Test Scenarios
1. **Happy Path**: Enter username/password, tap Login, observe callback.
2. **Edge Cases**:
   - Rotate device – ensure fields retain text.
   - Toggle password eye icon repeatedly.
3. **Error Handling**:
   - Not yet implemented; confirm UI remains responsive if backend returns failure (once wired).

### Manual Test Checklist
- [ ] Header + logo render across breakpoints.
- [ ] Inputs respond to keyboard actions (Next).
- [ ] Buttons feed correct callbacks.
- [ ] Scroll behavior works on smaller displays.

## Implementation Notes

### Technical Approach
- Compose layouts reuse the same component set as the PIN screen.
- Placeholder callbacks allow future integration with employee auth API.

### File Structure
```
app/src/main/java/com/iotoms/ui/auth/emp/password/
├── EmployeePasswordLoginScreen.kt
├── EmployeePasswordLoginScreenCompact.kt
└── EmployeePasswordLoginScreenExpanded.kt
```

### Key Components
- **EmployeePasswordLoginScreen**: Houses state and chooses layout.
- **Compact/Expanded variants**: Adjust spacing/panels for portrait vs. landscape.

### Dependencies
- Shared theme + button components.

### Implementation Checklist
- [x] Input fields + validation toggles.
- [x] CTA buttons + callbacks.
- [x] Orientation support with scroll handling.

## Dependencies

### Blocked By
- Base auth navigation from REG-0001.

### Blocks
- REG-0007: Employee password API integration.
- REG-0004: Forgot-password flow uses this entry point.

### Related Stories
- REG-0002 shares layout patterns.

## Notes
- When wiring to backend, reuse `Result` + `FormError` pattern from merchant login for consistency.
