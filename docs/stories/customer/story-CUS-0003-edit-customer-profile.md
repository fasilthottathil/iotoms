# Edit Customer Profile

**Story ID**: CUS-0003
**Priority**: Medium
**Status**: Completed

## Requirement

Deliver an editable customer form mirroring the view layout so associates can update customer info. The form should surface the same fields (name, phone, email, DOB), respect orientation changes, and reuse the avatar component.

**User Value**: Staff can correct customer details on the spot.
**Business Impact**: Improves data quality and customer satisfaction.

## Design Context

- Same structure as view screen but with editable text boxes.
- Scroll container for portrait; side-by-side layout for landscape.
- Floating action button placeholder to save.

**Design Assets**:
- Compose `OutlinedTextBox`, theme spacing, and avatar placeholder from `ViewCustomer`.

## User Flow

### Entry Points
- Edit FAB from view screen.
- Add customer CTA from directory.

### Flow Steps
1. `EditCustomerScreen` renders orientation-appropriate layout.
2. User edits text boxes (currently storing placeholder text).
3. Save FAB triggers callback (future API integration).

### Exit Points
- Save → future API call + return to detail.
- Cancel/back → discard edits and return to previous screen.

## Acceptance Criteria

1. **Editable Fields**:
   - Text boxes set `readOnly = false`.
   - Value placeholders show sample text until bound to data.
2. **Layout**:
   - Scrollable column in portrait with central avatar.
   - Expanded layout ready for side-by-side embedding.
3. **Actions**:
   - Floating action button with edit icon triggers save callback placeholder.
4. **State Persistence**:
   - Inputs survive rotation via `rememberSaveable` wrappers inside `EditCustomerScreen`.

## Testing Notes

### Test Scenarios
1. **Happy Path**: Modify fields, tap save, ensure callback fires.
2. **Edge Cases**:
   - Clear fields to empty values.
   - Rotate device while editing and confirm text remains.

### Manual Test Checklist
- [ ] Inputs accept keyboard text.
- [ ] Scroll/spacing works across breakpoints.
- [ ] Save FAB reachable and clickable.

## Implementation Notes

### Technical Approach
- Similar to view screen but with `readOnly = false`.
- Layout declared in `EditCustomerScreenCompact/Expanded`.

### File Structure
```
app/src/main/java/com/iotoms/ui/customer/edit/
├── EditCustomerScreen.kt
├── EditCustomerScreenCompact.kt
└── EditCustomerScreenExpanded.kt
```

### Key Components
- **EditCustomerScreen**: Scaffold + FAB + orientation logic.
- **Compact/Expanded**: Compose layout for each breakpoint.

### Dependencies
- Shared components + theme tokens.

### Implementation Checklist
- [x] Editable text boxes.
- [x] Avatar placeholders.
- [x] FAB hooking into callback.
- [x] Orientation handling.

## Dependencies

### Blocked By
- CUS-0002 (view screen) for navigation context.

### Blocks
- Future story wiring fields to customer repository and validation.

### Related Stories
- CUS-0001 (directory) will navigate here when adding customers.

## Notes
- Add validation + error states once backend contracts defined.
