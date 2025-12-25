# Cart Experience

## Overview
The cart feature renders the primary selling workspace, including the navigation drawer, quick-pick grid, and calculator toggle used to ring up general items.

## Business Context
Cashiers need a fast, kiosk-ready UI to manage carts, swap transactions, hold orders, and add ad-hoc items. This shell provides the foundational UX for those flows.

## User Value
- Access drawer controls such as configurations, return, hold, customer assignment, and clear cart.
- Add items via quick picks or the general item calculator keypad.

## Stories Overview

| Story ID | Title | Priority | Status | Description |
|----------|-------|----------|--------|-------------|
| CART-0001 | Cart Shell & Drawer | Critical | Completed | Drawer-enabled cart screen with responsive layouts and quick pick/product grid |
| CART-0002 | General Item Calculator | High | Completed | Numeric calculator that toggles into the cart to add ad-hoc amounts |

## User Flows
- [Cart Shell Flow](./user-flows/cart-shell-flow.md)

## Implementation Status
- [x] Story CART-0001 - Cart Shell & Drawer
- [x] Story CART-0002 - General Item Calculator

## Technical Overview

### Components
- **CartScreen**: Hosts the drawer, app bar actions, and toggles.
- **CartScreenCompact/Expanded**: Arrange quick pick and product grid layouts per orientation.
- **GeneralItemCalculatorScreen**: Keypad UI for manual entries.
- **cartDrawerItem / ProductItem / QuickPickItem / CartItem**: Shared components for repeated UI.

### Domain & Data
- Current implementation is UI-only; data wiring to cart repositories will happen in later stories.

### State Management
- Local `rememberSaveable` booleans determine whether the calculator is visible.
- Drawer uses `rememberDrawerState` + coroutine scope for toggling.

## Dependencies
- Material 3 Compose, adaptive window heuristics, shared theme tokens in `ui/theme`.

## Future Enhancements
- Hook add-to-cart events to `CartRepository`.
- Populate quick pick/product lists from live data.
- Wire drawer actions to navigation or dialogs.
