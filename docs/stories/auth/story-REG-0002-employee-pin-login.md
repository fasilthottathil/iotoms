# Employee PIN Login UI

**Story ID**: REG-0002
**Priority**: High
**Status**: Completed

## Requirement

Deliver a PIN-based authentication experience so employees can quickly re-enter the kiosk without full credentials. The UI must include a secure keypad, ability to toggle PIN visibility, and parity between portrait and landscape layouts.

**User Value**: Floor staff can resume work with a numeric-only interaction.
**Business Impact**: Reduces downtime and security risk between handoffs.

## Design Context

- Header gradient with brand colors and logo.
- Read-only PIN textbox that mirrors typed digits with optional show/hide icon.
- Numeric keypad + action row (clear, zero, submit) sized to kiosk-friendly touch targets.
- CTA to switch to password login to support fallback cases.

**Design Assets**:
- Colors/fonts pulled from `ui/theme`.
- PIN keypad references Material 3 button styles (`FilledButton`, `OutlinedButton`).

## User Flow

### Entry Points
- "Employee PIN Login" action from kiosk or merchant home.
- Link inside Employee Password login.

### Flow Steps
1. Render `EmployeePinLoginScreen` variant based on orientation.
   - User action: Input digits via keypad.
   - System response: Local state updated through `rememberSaveable`.
2. User optionally toggles PIN visibility.
   - System response: Switch between `PasswordVisualTransformation` and plain text.
3. User taps submit or switches to password login.
   - System response: Placeholder callbacks (backend integration tracked separately).

### Exit Points
- Success: Will navigate to secured destination once API is integrated (currently TODO).
- Cancel: Back presses or "Login With Password" action route elsewhere.
- Error: Clearing digits resets state locally.

## Acceptance Criteria

1. **Orientation Support**: Compact vs. expanded versions render automatically per `DeviceOrientation`.
2. **PIN Capture**:
   - Keypad appends digits to the `pin` state.
   - Clear button removes last digit; keypad digits loop across rows and final row includes 0.
3. **Visibility Toggle**: Trailing icon toggles between masked/unmasked states.
4. **Actions**:
   - Submit button triggers `onLoginClick`.
   - Secondary button labeled "Login With Password" triggers `onPasswordLoginClick`.
5. **Visuals**: Header gradient uses `PrimaryTeal`/`PrimaryTealLight` and displays the iotoms logo.

## Testing Notes

### Test Scenarios
1. **Happy Path**: Enter digits, toggle visibility, submit, confirm callback receives PIN.
2. **Edge Cases**:
   - Delete when empty should no-op.
   - Orientation change retains pin via `rememberSaveable`.
3. **Error Handling**:
   - Since backend not wired, ensure UI gracefully resets when `onLoginClick` clears state.

### Manual Test Checklist
- [ ] Portrait & landscape layouts show keypad/body as intended.
- [ ] Buttons respect kiosk touch guidelines.
- [ ] Visibility toggle updates text immediately.
- [ ] Switching to password login triggers callback.

## Implementation Notes

### Technical Approach
- Compose-only implementation with state hoisted to `EmployeePinLoginScreen`.
- Reuses shared button components for consistent styles.
- Will pipe into employee auth API once backend contract is in place.

### File Structure
```
app/src/main/java/com/iotoms/ui/auth/emp/pin/
├── EmployeePinLoginScreen.kt
├── EmployeePinLoginScreenCompact.kt
└── EmployeePinLoginScreenExpanded.kt
```

### Key Components
- **EmployeePinLoginScreen**: Detects orientation and feeds state + callbacks into layout variants.
- **Compact/Expanded layouts**: Display keypad, header, CTA arrangement for each breakpoint.

### Dependencies
- None beyond Compose + shared components.

### Implementation Checklist
- [x] Orientation-aware layouts.
- [x] Keypad interactions and state persistence.
- [x] Visibility toggle.
- [x] Callback wiring for login/password links.

## Dependencies

### Blocked By
- Merchant login shell (REG-0001) for entry navigation.

### Blocks
- REG-0006: Employee PIN backend integration.

### Related Stories
- REG-0003 (password login) shares layout assets.
- REG-0004 (forgot password) referenced via fallback flow.

## Notes
- Keep keypad actions accessible; switch to Compose `LazyVerticalGrid` if we ever need dynamic layouts.
