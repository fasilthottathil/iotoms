# Customer Directory Shell

**Story ID**: CUS-0001
**Priority**: High
**Status**: Completed

## Requirement

Provide the top-level customer browsing experience with search, grid layout, and orientation-specific actions (FAB vs. extended button). This shell lays the groundwork for live customer data and navigation into detail/edit screens.

**User Value**: Associates can quickly locate customers while multitasking.
**Business Impact**: Supports loyalty programs and customer service use cases at the kiosk.

## Design Context

- Material 3 top app bar with title + back arrow.
- Action area: Extended FAB in landscape, standard FAB in portrait.
- Search field anchored at top of list/grid.
- Grid of `CustomerItem` tiles with spacing/padding tokens.

**Design Assets**:
- Compose components from `ui/components`.
- Theme tokens in `ui/theme`.

## User Flow

### Entry Points
- Customers menu selection from nav.

### Flow Steps
1. App bar renders with "Customers" title and back icon.
2. Orientation detection chooses compact vs. expanded layout.
3. Search field sits above grid; user types to filter (stubbed for now).
4. Grid of placeholder `CustomerItem`s renders; landscape adds inline detail area.

### Exit Points
- Back icon returns to previous module.
- Floating action button transitions to add/edit customer (future navigation).

## Acceptance Criteria

1. **Orientation Behavior**:
   - Portrait uses FAB and full-screen grid.
   - Landscape uses Extended FAB in the app bar and splits view to include detail pane.
2. **Search Field**:
   - Shown in both layouts using `OutlinedTextBox`.
   - Input captured locally even without backend.
3. **Grid Rendering**:
   - `LazyVerticalGrid` with adaptive 200dp min width.
   - Each entry uses `CustomerItem`.
4. **Top App Bar**:
   - Displays title, back arrow, and optional extended FAB.
5. **Spacing & Theme**: Respects `ExtraSmallPadding`, Material 3 colors.

## Testing Notes

### Test Scenarios
1. **Happy Path**: Scroll grid, tap search, open FAB.
2. **Edge Cases**:
   - Rotate device to verify layout swap.
   - Scroll list while Extended FAB visible.

### Manual Test Checklist
- [ ] Back arrow responds.
- [ ] FAB/extended button clickable.
- [ ] Search field focusable with IME.
- [ ] Grid spacing consistent.

## Implementation Notes

### Technical Approach
- Compose `Scaffold` hosts top app bar + content, plus orientation-specific FAB slot.
- `CustomersScreen` decides layout using `getDeviceOrientation()`.

### File Structure
```
app/src/main/java/com/iotoms/ui/customer/
├── CustomersScreen.kt
├── CustomersScreenCompact.kt
└── CustomersScreenExpanded.kt
app/src/main/java/com/iotoms/ui/components/CustomerItem.kt
```

### Key Components
- **CustomersScreen**: Router/composition root.
- **CustomersScreenCompact/Expanded**: Layouts for different breakpoints.
- **CustomerItem**: Card placeholder.

### Dependencies
- Compose Material 3, internal theme tokens.

### Implementation Checklist
- [x] App bar + FAB.
- [x] Search field + grid layout.
- [x] Orientation-specific UI.

## Dependencies

### Blocked By
- None.

### Blocks
- CUS-0002/0003 (detail/edit) rely on this entry point.

### Related Stories
- CUS-0002 uses inline detail area from expanded layout.

## Notes
- Integrate real data via repository + paging in future sprints.
