# Customer Management

## Overview
Customer Management provides list, detail, and edit experiences for kiosk associates. It supports quick searching, inline viewing, and editing of customer profiles with orientation-aware layouts.

## Business Context
Associates must locate, review, and modify customer profiles while ringing transactions or managing loyalty. These screens prepare the ground for wiring APIs to the existing UI.

## User Value
- Browse/search customers with responsive layouts.
- Preview customer details alongside the list on large displays.
- Edit customer information with consistent fields.

## Stories Overview

| Story ID | Title | Priority | Status | Description |
|----------|-------|----------|--------|-------------|
| CUS-0001 | Customer Directory Shell | High | Completed | Customer list with search, FABs, and orientation handling |
| CUS-0002 | View Customer Details | Medium | Completed | Read-only detail panel embedded in list or standalone |
| CUS-0003 | Edit Customer Profile | Medium | Completed | Editable form mirroring detail layout |

## User Flows
- [Customer Lifecycle Flow](./user-flows/customer-lifecycle-flow.md)

## Implementation Status
- [x] Story CUS-0001 - Customer Directory Shell
- [x] Story CUS-0002 - View Customer Details
- [x] Story CUS-0003 - Edit Customer Profile

## Technical Overview

### Components
- **CustomersScreen**: Hosts top app bar, FAB(s), and chooses compact/expanded layout.
- **CustomersScreenCompact/Expanded**: Search box, grid list, and inline detail slot in expanded mode.
- **CustomerItem**: Card used throughout list/grid.
- **ViewCustomerScreen** + variants: Read-only profile view.
- **EditCustomerScreen** + variants: Editable profile form.

### State Management
- Local state only for now (search field and list data hard-coded). API integration will replace placeholders.

### Dependencies
- Compose Material 3, shared components, and theming tokens. Orientation detection via `getDeviceOrientation`.

## Future Enhancements
- Hook customer list/search to repository and pagination.
- Connect view/edit forms to domain models and validation.
- Add navigation to/from cart/customer flows.
