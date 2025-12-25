# View Customer Detail

**Story ID**: CUS-0002
**Priority**: Medium
**Status**: Completed

## Requirement

Render a read-only customer profile panel that can live as a standalone screen (portrait) or inline with the list (landscape). The panel should display avatar placeholder and key attributes.

**User Value**: Associates review pertinent details while assisting customers.
**Business Impact**: Reduces mistakes during loyalty checks or account confirmation.

## Design Context

- Circular avatar placeholder with themed background.
- Stacked `OutlinedTextBox` widgets marked `readOnly`.
- Scrollable column for small screens.
- Floating action button with edit icon when used standalone.

**Design Assets**:
- Colors from `NeutralGray` palette, spacing tokens from theme.

## User Flow

### Entry Points
- Customers list selection (inline in landscape) or navigation to detail view.

### Flow Steps
1. Determine orientation and render compact vs. expanded layout.
2. Show avatar, then each attribute inside read-only text boxes.
3. Provide edit FAB to transition to edit flow.

### Exit Points
- Edit FAB opens edit story (CUS-0003).
- Back navigation returns to customer list.

## Acceptance Criteria

1. **Layout**:
   - Compact mode uses `Column` with `verticalScroll`.
   - Expanded mode can live alongside list or standalone inside detail pane.
2. **Fields**:
   - Name, phone, email, DOB fields shown and set `readOnly = true`.
3. **Avatar**:
   - Circular placeholder uses `NeutralGray600` background and `ic_launcher_background`.
4. **Actions**:
   - Floating action button with Edit icon triggers callback (open edit screen).

## Testing Notes

### Test Scenarios
1. **Happy Path**: Scroll detail view, ensure fields anchored.
2. **Edge Cases**:
   - Orientation change retains placeholder data.
   - Scroll when embedded in `CustomersScreenExpanded`.

### Manual Test Checklist
- [ ] FAB visible and clickable.
- [ ] Fields non-editable.
- [ ] Layout scrolls when content exceeds height.

## Implementation Notes

### Technical Approach
- Compose `Scaffold` for base screen; detail content built via `ViewCustomerScreenCompact/Expanded`.
- Inline usage simply calls `ViewCustomerScreen()` inside `CustomersScreenExpanded`.

### File Structure
```
app/src/main/java/com/iotoms/ui/customer/view/
├── ViewCustomerScreen.kt
├── ViewCustomerScreenCompact.kt
└── ViewCustomerScreenExpanded.kt
```

### Key Components
- **ViewCustomerScreen**: Scaffold + FAB wrapper.
- **Compact/Expanded variants**: Layout differences + scroll handling.

### Dependencies
- Shared `OutlinedTextBox`, theme tokens.

### Implementation Checklist
- [x] Read-only text boxes.
- [x] Avatar placeholder.
- [x] FAB linking to edit.
- [x] Orientation handling.

## Dependencies

### Blocked By
- CUS-0001 layout for inline usage.

### Blocks
- CUS-0003 uses this screen as entry.

### Related Stories
- Cart module may open this view when linking a customer.

## Notes
- Replace static text with bound data once repository integrated.
