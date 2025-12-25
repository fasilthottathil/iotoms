# Customer Lifecycle Flow - User Flow

## Overview
Maps how associates browse customers, open a detail pane, and transition into edit mode.

## Entry Points
1. **Customers Menu**: Selecting “Customers” from the app nav.
2. **Cart CTA**: Tapping the CUSTOMER action on the cart toolbar (future nav).

## Flow Diagram

```
[CustomersScreen] → [Search Box] → [Customer Grid]
          ├─ Select customer (portrait) → [ViewCustomerScreen] (full screen)
          └─ Select customer (landscape) → [Inline Detail Pane]
                                   └─ Tap Edit → [EditCustomerScreen] → [Save/Back]
```

## Detailed Steps

### Step 1: Open Customer Directory
**Screen**: `CustomersScreen`
**User Action**: View top app bar and optional FAB (depends on orientation).
**System Response**: `getDeviceOrientation()` chooses compact or expanded layout.
**UI Elements**: Search box, grid of `CustomerItem`s, optional inline detail pane.

### Step 2: Search or Scroll
**Screen**: `CustomersScreenCompact/Expanded`
**User Action**: Type into search field (currently stubbed) or scroll grid.
**System Response**: Input stored locally; dataset static placeholder.

### Step 3: View Customer
**Screen**: `ViewCustomerScreen`
**User Action**: Select list entry (implied) to load detail view.
**System Response**: Shows avatar placeholder and read-only fields.
**UI Elements**: Avatar, text boxes for name/number/email/DOB.

### Step 4: Edit Customer
**Screen**: `EditCustomerScreen`
**User Action**: Tap floating action button (portrait) or inline icon (landscape) to enter edit form.
**System Response**: Reuses layout but text boxes editable.

## Success Scenarios
1. **Compact Devices**: FAB opens add/edit flows; detail view occupies entire screen.
2. **Expanded Devices**: List and detail side-by-side reduce navigation.
3. **Editing**: Input fields accept edits and scroll vertically.

## Error Scenarios
1. **Orientation Switch**: Ensure selection persists (to be implemented when data layer is wired).
2. **Empty Data**: Layout still renders gracefully with placeholders.

## Exit Points
1. **Success**: Edits saved (future) or detail viewed.
2. **Cancel**: Back button returns to previous view.
3. **Navigation**: App bar Back icon routes to prior module.
