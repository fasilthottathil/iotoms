# Data Synchronization

## Overview
The sync feature guarantees that catalogs, customers, taxes, and configuration data are available offline before the cart shell loads. It is kicked off immediately after a successful merchant login and blocks navigation until every dataset is cached locally.

## Business Context
Stores cannot transact without a fresh local cache. Running a foreground WorkManager job keeps the kiosk compliant with Android 14+ policies while ensuring a full sync happens before cashiers see the cart UI.

## User Value
- Guarantees that catalog data, pay modes, and registers are ready the moment a cashier lands on Cart.
- Provides visual feedback (modal dialog + progress) during the seeding process.

## Stories Overview

| Story ID | Title | Priority | Status | Description |
|----------|-------|----------|--------|-------------|
| SYNC-0001 | Data Sync Modal & Worker | Critical | Completed | Foreground WorkManager job + blocking dialog that seeds the Room cache after login |

## Implementation Status
- [x] SYNC-0001 – Data Sync Modal & Worker

## Technical Overview

### Components
- **DataSyncDialogScreen**: Compose modal that surfaces progress and blocks interaction.
- **DataSyncViewModel/DataSyncUiState**: Initiate sync and expose progress via `StateFlow`.
- **DataSyncWorker**: Foreground WorkManager job that runs the sync use case and reports progress.
- **DataSyncRepositoryImpl**: Coordinates repository calls for items, customers, attributes, business metadata, taxes, pay modes, and users.

### Dependencies
- AndroidX WorkManager + Koin worker factory.
- Room DAOs/entities for plans, stores, venues, registers, branding, items, customers, taxes, attributes, and users.
- Ktor repositories for API calls listed in `ApiUrl`.

## Future Enhancements
- Manual “Re-sync data” entry in the app shell for operators.
- Error surface with retry/diagnostics when synchronization fails.
