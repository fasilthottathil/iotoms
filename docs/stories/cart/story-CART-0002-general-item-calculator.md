# General Item Calculator

**Story ID**: CART-0002
**Priority**: High
**Status**: Completed

## Requirement

Allow cashiers to ring up ad-hoc merchandise by entering a custom amount directly inside the cart screen. Provide a numeric keypad, clear/backspace options, amount formatting, and an Add button that emits the entered total.

**User Value**: Staff can quickly sell miscellaneous items without creating catalog SKUs.
**Business Impact**: Supports flexible operations and reduces friction at checkout.

## Design Context

- Slide-in panel within the cart screen toggled by the dialpad icon.
- Numeric keypad arranged in 3x4 grid with large buttons and accessible labels.
- Header text field shows formatted currency as digits are entered.
- Add/Clear buttons sized using `AppBarHeight`.

**Design Assets**:
- Reuses `OutlinedTextBox`, `FilledButton`, `OutlinedButton`.
- Colors/padding from `ui/theme`.

## User Flow

### Entry Points
- Dialpad icon on the cart toolbar (portrait) or the equivalent action in landscape.

### Flow Steps
1. User taps dialpad icon, `canShowGeneralCalculator` toggles true.
2. Calculator renders with read-only amount field and keypad.
3. User taps digits; amount formats via `formatAmount()`.
4. User taps Add to emit amount to parent; screen stays or toggles off manually.

### Exit Points
- Success: Amount passed to `onClickAdd` (currently placeholder) and ready to be inserted into cart.
- Cancel: User toggles dialpad again to return to product grid.

## Acceptance Criteria

1. **Display Field**:
   - Shows formatted numeric string, read-only, and updates in real time.
2. **Keypad Layout**:
   - 1–9 arranged in three rows, final row has Clear, 0, Add buttons.
   - Buttons sized to `AppBarHeight` for accessible touch targets.
3. **State Handling**:
   - `amount` stored via `rememberSaveable`.
   - Clear removes last digit; entering digits respects `formatAmount`.
4. **Add Callback**:
   - `onClickAdd` invoked with the formatted amount.
   - After Add, future story can reset amount (current behavior retains value).

## Testing Notes

### Test Scenarios
1. **Happy Path**: Enter digits, press Add, verify callback receives currency string.
2. **Edge Cases**:
   - Clear when string empty.
   - Add when string empty returns empty string, no crash.
   - Rapid taps maintain formatting.
3. **Error Handling**:
   - Input limited to digits; ensure no decimals introduced.

### Manual Test Checklist
- [ ] Dialpad toggle switches between calculator and product grid.
- [ ] Buttons animate (ripple) and respond instantly.
- [ ] Amount formatting stable during rotation.
- [ ] Add button respects disabled state if added later (currently always enabled).

## Implementation Notes

### Technical Approach
- Standalone composable inserted inside cart layout when toggle true.
- Uses `rememberSaveable` string and helper extension `formatAmount()` for formatting.
- Layout uses Compose `Row` + `Column` for keypad grid.

### File Structure
```
app/src/main/java/com/iotoms/ui/cart/GeneralItemCalculatorScreen.kt
```

### Key Components
- **GeneralItemCalculatorScreen**: Handles keypad UI and amount state.
- **CartScreen**: Hosts toggle and receives callback.

### Dependencies
- UI components package + `formatAmount` extension from `com.iotoms.utils.extensions`.

### Implementation Checklist
- [x] Toggle integration from cart shell.
- [x] Numeric keypad interactions.
- [x] Callback for Add.
- [x] Clear/backspace controls.

## Dependencies

### Blocked By
- CART-0001 (cart shell to host the calculator).

### Blocks
- CART-0003: Hook Add callback into cart data model.

### Related Stories
- None yet.

## Notes
- Future iteration should disable Add when amount empty and show error text.
