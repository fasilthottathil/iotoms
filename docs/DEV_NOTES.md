# Developer Notes

## Overview
iotoms is a single-module Android application written in Kotlin 2.2.x and powered entirely by Jetpack Compose. The app follows a layered architecture:

- `com.iotoms.ui` – Compose screens, state holders, theming, and navigation (`IotomsNavigation` based on Navigation 3).
- `com.iotoms.domain` – Use cases and repository interfaces that define business rules.
- `com.iotoms.data` – Remote (Ktor), local (Room + SharedPreferences via `AppPreference`), mappers, and repository implementations.
- `com.iotoms.di` – Koin modules wiring the graph (`App` starts Koin with storage, network, repository, useCase, viewModel, worker modules).

The project targets SDK 36 (min 24) with Material 3, adaptive navigation suite, Navigation 3, and Compose BOM `2025.11.01`. Networking relies on Ktor + OkHttp with bearer-token auth headers sourced from preferences, while persistence is handled through Room entities/DAOs and SharedPreferences for lightweight session data. Background tasks run through WorkManager (`DataSyncWorker`) with Koin-provided dependencies.

## Recent Changes

**December 2025**
- Added `RegisterUseCase` and `AuthenticationRepositoryImpl` to support login/register flows with Ktor + bearer token persistence.
- Expanded `AppPreference` helpers for domain + token storage used by API headers.

**November 2025**
- Introduced Jetpack Navigation 3 setup (`IotomsNavigation`) with Compose destinations + lifecycle-aware state collection.
- Added Room database (`AppDatabase`) covering cart, catalog, and user entities plus storage module wiring.
- Created `DataSyncWorker` and the WorkManager Koin bindings for background synchronization.

**October 2025**
- Bootstrapped base Koin modules (network, repository, use case) and started `App` to initialize dependency graph.
- Established Ktor `ApiClient` with JSON serialization, logging, timeout, tenant headers, and bearer auth hooks.

## User Preferences & Working Agreements
- **Story-first delivery**: Every change references a BMAD story with acceptance criteria/user flows (see `docs/WORKFLOW_GUIDELINES.md`).
- **Compose-only UI**: No XML layouts; use Material 3 components, theming helpers in `ui/theme`, and `collectAsStateWithLifecycle`.
- **Layer boundaries**: UI never calls Ktor/Room directly; communication flows UI → domain use cases → repositories → data sources.
- **DI via Koin**: Add new bindings inside dedicated module files; prefer constructor injection in ViewModels/use cases/workers.
- **Testing expectations**: Unit tests for use cases/repositories (`./gradlew testDebugUnitTest`) and Compose UI tests in `androidTest` (`./gradlew connectedDebugAndroidTest`). Mock flows/state when possible.
- **Documentation sync**: Update `docs/stories/*` and this document whenever architecture, dependencies, or standards change.
- **Error handling**: Use `com.iotoms.utils.Result` to normalize success/error from repositories and surface rich `LoginUiState`-like models.

## System Architecture

### Module Layout & Key Packages
- `app/` – only Gradle module today; structured by vertical slices inside `com.iotoms`.
- `ui/` contains feature folders (`auth`, `cart`, `customer`, etc.), shared components, theme, and navigation.
- `domain/` hosts repository interfaces and use cases organized by capability (`auth`, `paymode`, ...).
- `data/` holds API clients (`remote/api`), DTOs (`model`), Room entities/DAO/DB (`local`), repositories, and background workers.
- `di/` glues everything through named modules loaded in `App`.

### UI Layer
- Compose-only screens (`LoginScreen`, etc.) powered by Material 3 + adaptive layout helpers.
- Navigation uses Navigation 3 (`NavDisplay`, `rememberNavBackStack`, entry decorators for saved state + ViewModel store).
- ViewModels expose `StateFlow` (`MutableStateFlow` + `stateIn`). Use `collectAsStateWithLifecycle` from lifecycle-compose.
- UI state models follow sealed interfaces/classes (see `LoginUiState`) to cover idle/loading/content/error.
- Theme + typography live in `ui/theme`. Follow height-aware responsive design and prefer Material icons from Compose libs.

### Domain Layer
- Use cases represent atomic business actions (e.g., `RegisterUseCase`, `GetPayModesUseCase`).
- Repository interfaces describe available operations (Auth, Cart, Tax, etc.) and are dependency-injected into use cases.
- Domain returns `Result` wrappers so UI/state machines can handle success/error/validation consistently.

### Data Layer
- **Networking**: `ApiClient` builds a `HttpClient` (OkHttp engine) with Logging, ContentNegotiation (kotlinx serialization), bearer Auth that pulls tokens from `AppPreference`, default tenant header, and HttpTimeout.
- **API helpers**: `apiRequest` wraps HTTP calls into `Result` objects and standardizes `ApiError`.
- **Persistence**: Room database via `AppDatabase` with numerous catalog entities and DAO accessors. SharedPreferences (AppPreference) persist session + tenant metadata. Room is built in `storageModule`.
- **Repositories**: Concrete classes (e.g., `AuthenticationRepositoryImpl`) orchestrate remote/local sources and map responses into domain models.
- **Workers**: `DataSyncWorker` extends `CoroutineWorker` and delegates to domain repositories for background sync tasks.

### Dependency Injection
- Koin modules:
  - `networkModule`: Provides `HttpClient`.
  - `storageModule`: Provides `AppDatabase` and `AppPreference`.
  - `repositoryModule`: Binds domain repositories to data implementations.
  - `useCaseModule`: Exposes use case factories.
  - `viewModelModule`: Registers ViewModels with constructor injection.
  - `workerModule`: Registers WorkManager workers with dependency graph access.
- `App` starts Koin in `Application.onCreate()` with logger + WorkManager factory, ensuring workers receive dependencies.

### Background Work
- WorkManager powers periodic/one-off sync tasks through `DataSyncWorker`. Enqueue through domain repositories or use cases.
- Keep worker inputs serializable; inject dependencies via Koin worker DSL.

### Build, Testing & Tooling
- **Build**: `./gradlew assembleDebug` for dev builds; `./gradlew bundleRelease` for store packages.
- **Unit tests**: `./gradlew testDebugUnitTest` covers domain/data.
- **Instrumented/Compose UI tests**: `./gradlew connectedDebugAndroidTest`.
- **Static analysis**: Android Gradle Plugin Lint is available via `./gradlew lintDebug` (run before release).
- **Version catalog**: `gradle/libs.versions.toml` controls dependencies; update there first.
- **KSP**: Room compiler is wired via `ksp` plugin; add new annotated classes accordingly.

### Release & Configuration Notes
- Application ID: `com.iotoms`.
- `compileSdk`/`targetSdk`: 36; `minSdk`: 24.
- JVM target 11; Compose enabled in `buildFeatures`.
- Maintain tenant headers/authorization flows by ensuring `AppPreference` is updated after auth calls.
- Navigation start destination currently `Login`; add more entries via `entryProvider` builder.

## External Dependencies

### UI & Compose
- Jetpack Compose BOM `2025.11.01`, Material 3, Material Icons, Adaptive Navigation Suite (`material3-adaptive-navigation-suite`).
- Lifecycle Compose utilities for `collectAsStateWithLifecycle`.

### Navigation & State
- AndroidX Navigation 3 runtime/ui + lifecycle ViewModel decorators for Compose-first navigation state handling.

### Dependency Injection & Background
- Koin 4.1.x for ViewModel, WorkManager, and module definitions.
- WorkManager `androidx.work:work-runtime-ktx` for background sync.

### Networking & Serialization
- Ktor client (core, OkHttp engine, auth, logging, content negotiation) and kotlinx-serialization JSON.
- Gson available for interoperability.

### Persistence
- Room runtime, KTX, paging, and compiler via KSP for catalog/cart/user data.
- SharedPreferences wrapper (`AppPreference`) for lightweight data.

### Testing
- JUnit 4, AndroidX test JUnit extensions, Espresso, Compose UI test artifacts.

### Build Plugins
- Android Gradle Plugin 8.13, Kotlin Android plugin 2.2.21, Kotlin Compose plugin, Kotlin serialization plugin, and KSP for Room.

Keep this file updated when architecture, tooling, or processes change so onboarding engineers always have an accurate reference.
