# Cart Shell Flow - User Flow

## Overview
Describes how a cashier interacts with the cart screen, opens the drawer, toggles between quick picks and the general item calculator, and manages toolbar actions.

## Entry Points
1. **Authenticated Home**: Selecting the Cart tab/screen.
2. **Navigation Drawer**: Quick access from any module when switching active carts.

## Flow Diagram

```
[CartScreen] → [Top App Bar]
         ├─ Tap menu icon → [ModalNavigationDrawer] (open/close)
         ├─ Tap RETURN/HOLD/CUSTOMER/CLEAR → [TODO actions]
         └─ Toggle Dialpad icon?
                 ├─ Yes → [GeneralItemCalculatorScreen]
                 └─ No  → [QuickPickRow + Product Grid]
```

## Detailed Steps

### Step 1: Launch Cart Screen
**Screen**: `CartScreen`
**User Action**: View grid or open drawer.
**System Response**: Drawer managed via `rememberDrawerState`, coroutines open/close.
**UI Elements**: Modal drawer, top app bar actions, orientation-specific content slot.

### Step 2: Drawer Interaction
**Screen**: `ModalDrawerSheet`
**User Action**: Scroll through `cartDrawerItem()` entries, open Configurations row.
**System Response**: Drawer stays open until closed by icon/backdrop tap.

### Step 3: Toggle Calculator
**Screen**: `CartScreenCompact` or `CartScreenExpanded`
**User Action**: Tap Dialpad icon in the compact toolbar (or the action exposed in expanded layout).
**System Response**: `canShowGeneralCalculator` boolean flips and chooses between `GeneralItemCalculatorScreen` and product UI.

### Step 4: Add General Item
**Screen**: `GeneralItemCalculatorScreen`
**User Action**: Tap keypad digits, use clear/backspace, add item.
**System Response**: Local `amount` state formats text via `formatAmount()`. Add callback surfaces total to parent (currently placeholder).

### Exit Points
1. **Success**: Item amount captured; future story will push it into `CartItem`.
2. **Cancel**: User closes drawer or toggles back to product grid.
3. **Error**: Not applicable yet; keypad guards against invalid input via numeric-only interactions.

## Success Scenarios
1. **Drawer Control**: Menu icon toggles drawer open/close reliably.
2. **QuickPick Browsing**: Quick pick carousel + product grid scroll/responsive behavior in both orientations.
3. **Calculator Entry**: Amount builds using keypad and resets when clearing.

## Error Scenarios
1. **Orientation Change**: Ensure toggled state persists.
2. **Drawer Conflicts**: Prevent drawer actions while calculator is open (handled by Material drawer semantics).

## Exit Points
1. **Complete Action**: After using calculator or drawer the user returns to cart grid.
2. **Navigation**: Drawer Configurations row will route to settings (future).
