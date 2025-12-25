# Workflow Guidelines & Story-Driven Development (Android)

## Table of Contents
1. [Overview](#overview)
2. [Story-Driven Development Process](#story-driven-development-process)
3. [Story Structure (BMAD Format)](#story-structure-bmad-format)
4. [User Flow Documentation](#user-flow-documentation)
5. [Developer Notes Maintenance Guidelines](#developer-notes-maintenance-guidelines)
6. [Feature Development Workflow](#feature-development-workflow)
7. [Templates](#templates)

---

## Overview

This document captures the end-to-end workflow required to ship any feature in the native Android iotoms application. The app is a Kotlin/Jetpack Compose project with a layered architecture (`data` → `domain` → `ui`), dependency injection through Koin, networking via Ktor, Room-backed persistence, and Navigation 3 for screen flows. Every story must be documented, designed, implemented, and tested against the same set of expectations to maintain the integrity of the platform.

### Core Principles

1. **Story-First Development**: No Kotlin/Compose code is written before the story, user flow, and acceptance criteria exist.
2. **User Flow Clarity**: Each story references an explicit flow housed in `docs/stories/[feature]/user-flows/`.
3. **BMAD Format**: Business-Mockup-Acceptance-Definition is mandatory for all story files.
4. **Documentation Sync**: Update `docs/DEV_NOTES.md` (create if missing) whenever architecture, tooling, or preferences change.
5. **Iterative Refinement**: Requirements evolve as we learn from implementation; update stories accordingly.
6. **Compose-First Delivery**: Screens are implemented with Material 3 Compose, ViewModels, and StateFlow while respecting our design system.
7. **Android Parity**: Every feature must consider device classes (phone/tablet), offline/online states, and OS lifecycle behavior.

---

## Story-Driven Development Process

### Phase 1: Planning & Discovery

**Step 1: Feature Identification**
- Capture the business requirement or pain point.
- Define the scope and constraints for this release.
- Slot the work into an epic/feature area (Auth, Cart, Orders, etc.).
- Create the feature folder: `docs/stories/[feature-name]/`.
- Map the Android modules that will change (`app/src/main/java/com/iotoms/ui/[feature]/`, `domain`, `data`, `di`, etc.).

**Step 2: User Flow Mapping**
- Document the full Android user journey (entry → navigation → exit).
- Include UI states such as loading, offline, permission requests, and background sync.
- Identify every Compose screen, dialog, and system-level interaction (intents, notifications, background workers).
- Produce a diagram or narrative stored in `docs/stories/[feature]/user-flows/[flow-name].md`.

**Step 3: Story Breakdown**
- Break the feature into atomic user stories.
- Use the `FEATURE-CODE` naming scheme (`REG-0001`, `CART-0002`, etc.).
- Prioritize with Critical/High/Medium/Low labels.
- Capture dependencies (ViewModel work before UI, Room schema before repository, etc.).

### Phase 2: Story Documentation

**Step 1: Create Story Files**
```
docs/stories/
└── [feature-name]/
    ├── README.md                           # Feature narrative & story index
    ├── story-[ID]-[title-slug].md          # BMAD story file
    └── user-flows/
        └── [flow-name].md                  # Flow reference
```

**Step 2: Write Stories Using BMAD Format**
- Follow the [Story Structure](#story-structure-bmad-format) guidance.
- Reference mocked UI (Figma, Compose previews, screenshots).
- Note how the story maps to layers (`data`, `domain`, `ui`), DI modules, navigation entries, and background processing.

**Step 3: Review & Approval**
- Technical feasibility check (Compose, Koin modules, Room schema).
- UX/Design check for Material 3 compliance and states.
- Business review to confirm acceptance criteria and metrics.
- Approval is required before any Kotlin class or Composable is created.

### Phase 3: Implementation

**Step 1: Setup Development Environment**
- Create feature-specific packages under `app/src/main/java/com/iotoms/`:
  - `ui/[feature]/` for Compose screens, state, and UI models.
  - `domain/usecase/[feature]/` for use cases.
  - `data/remote|local|repository/[feature]/` for DTOs, DAOs, and repositories.
  - `di/` modules for wiring everything together in `App.kt`.
- Register navigation destinations in `ui/navigation/IotomsNavigation.kt` using Navigation 3 entry builders.
- Define/extend API endpoints inside the relevant Ktor service or remote data source.
- Update Room entities, DAO interfaces, and migrations where required.
- Add WorkManager workers when background sync or retry logic is needed.
- Declare resources (`strings.xml`, `colors`, assets) and `AndroidManifest` permissions if applicable.

**Step 2: Implement Stories**
- Work on one story at a time following the agreed priority.
- Build Composables that rely on `StateFlow` from ViewModels and use `collectAsStateWithLifecycle`.
- Keep UI logic inside the `ui` layer; business logic stays inside `domain` use cases and `data` repositories.
- Inject dependencies via Koin modules and ensure new bindings are covered by tests.
- Follow Material 3, Adaptive Navigation Suite, and responsive window size guidelines.
- Handle offline-first scenarios using Room caches and `Result` wrappers in `com.iotoms.utils`.

**Step 3: Testing**
- Execute unit tests with `./gradlew testDebugUnitTest`.
- Add/use instrumentation & Compose UI tests under `app/src/androidTest` and run `./gradlew connectedDebugAndroidTest`.
- Test WorkManager and background flows with custom test drivers or `TestWorkerBuilder`.
- Validate manual flows on emulator + physical device (small phone, Pixel 8/Pixel Fold, 8" tablet).
- Cover acceptance criteria, edge cases, accessibility, and rotation/fold states.

**Step 4: Documentation Updates**
- Update each story status (Planned → In Progress → Completed) in the feature README and story files.
- Capture implementation deviations, architecture changes, or library migrations in `docs/DEV_NOTES.md`.
- Log key learnings or new standards in a `Recent Changes` section.
- Link PRs or code references back to the story for traceability.

---

## Story Structure (BMAD Format)

### Required Sections

Every story file must include the following sections:

#### 1. Title & Story ID
```markdown
# [Story Title]

**Story ID**: [FEATURE-CODE]-[NUMBER]
**Priority**: Critical | High | Medium | Low
**Status**: Planned | In Progress | Completed | Blocked
```

#### 2. Requirement (Business Context)
```markdown
## Requirement

Business-level description of the outcome.

**User Value**: What Android user scenario does this unlock?
**Business Impact**: Why do we need it now?
```

#### 3. Design Context (Mockup/Visual Reference)
```markdown
## Design Context

- Compose UI requirements and Material components to use
- Figma links, screenshots, or Compose preview references
- Adaptive behaviors (phone vs. tablet, landscape, foldables)
- Accessibility expectations (TalkBack labels, touch targets, contrast)
- Theming & typography rules (`ui/theme`)

**Design Assets**:
- [Link to Figma]
- Assets saved under `docs/stories/[feature]/assets/`
```

#### 4. User Flow
```markdown
## User Flow

### Entry Points
- Identify nav routes or intents (deep links, notifications, quick actions)

### Flow Steps
1. [Step 1]
   - User action:
   - System response (ViewModel, repository, workers):
   - UI state (screen/dialog/snackbar):

### Exit Points
- Success states, pop/backstack destinations, WorkManager scheduling
- Cancel/back navigation
- Error and retry handling

### Decision Points
- Capture branching based on permissions, offline states, etc.
```

#### 5. Acceptance Criteria (Definition)
```markdown
## Acceptance Criteria

1. **[Criterion]**: Expected user-visible and system state
   - Include Compose UI specifics (animations, window size support)
   - Validation rules and error copy
2. **[API Integration]**: Required Ktor calls, payloads, headers
3. **[Data Persistence]**: Room entities/DAO usage, cache invalidation
4. **[State Management]**: ViewModel state, workers, background sync
5. **[Performance]**: Loading skeletons, pagination, offline fallback
```

#### 6. Testing Notes
```markdown
## Testing Notes

### Test Scenarios
1. **Happy Path**: Steps + expected Compose UI hierarchy
2. **Edge Cases**: Validation, offline, permissions, background kill
3. **Error Handling**: API failure, Room failures, worker timeouts

### Device Testing
- Pixel 5 (small)
- Pixel 8 Pro (baseline)
- Pixel Tablet / large-screen emulator
- Any OEM devices specific to deployment

### Manual Test Checklist
- [ ] Feature loads with correct nav entry
- [ ] Screen state survives rotation/process death
- [ ] Error banners/snackbars are actionable
- [ ] Back navigation matches flow spec
- [ ] Data persists/offline flow works
- [ ] Animations/accessibility verified
```

#### 7. Implementation Notes
```markdown
## Implementation Notes

### Technical Approach
- Architectural patterns, Compose state hoisting, background strategy

### File Structure
```
app/src/main/java/com/iotoms/
├── ui/[feature]/
│   ├── components/
│   ├── screen/
│   └── [Feature]ViewModel.kt
├── domain/usecase/[feature]/
│   └── [Action]UseCase.kt
├── data/
│   ├── remote/[feature]Api.kt
│   ├── local/[feature]Dao.kt
│   └── repository/[Feature]RepositoryImpl.kt
└── di/[Feature]Module.kt
```

### Key Components
- **Composable(s)**: Purpose, states, callbacks
- **ViewModel**: Inputs/outputs, dependencies
- **Repository/UseCase**: Business logic details

### API Endpoints
```kotlin
// Example in data/remote/auth/AuthApi.kt
suspend fun register(request: RegistrationRequest): Result<RegistrationResponse>
```

### State Management
```kotlin
sealed interface FeatureUiState {
    data object Idle : FeatureUiState
    data object Loading : FeatureUiState
    data class Content(val data: FeatureUiModel) : FeatureUiState
    data class Error(val message: String, val formError: FormError? = null) : FeatureUiState
}
```

### Dependencies
- New Gradle libraries, KSP processors, WorkManager constraints, permissions

### Implementation Checklist
- [ ] Create data/domain/ui scaffolding
- [ ] Add DI bindings in `di/*.kt`
- [ ] Register navigation entry
- [ ] Implement Compose UI with previews
- [ ] Add API + repository logic
- [ ] Wire Room entities/DAO/migrations
- [ ] Cover acceptance criteria via tests
- [ ] Update story/feature documentation
```

#### 8. Dependencies & Related Stories
```markdown
## Dependencies

### Blocked By
- [STORY-ID]: Must finish first (e.g., API ready, schema migration)

### Blocks
- [STORY-ID]: Stories depending on this delivery

### Related Stories
- [STORY-ID]: Non-blocking but contextually linked
```

---

## User Flow Documentation

### User Flow Template

Flows live in `docs/stories/[feature]/user-flows/` and describe every Android-specific step.

```markdown
# [Flow Name] - User Flow

## Overview
[Goal of this flow]

## Entry Points
1. **[Entry 1]**: Navigation route, deep link, widget, notification.
2. **[Entry 2]**: Another trigger.

## Flow Diagram

```
[Start] → [Compose Screen A] → [Decision]
                     ├─ Success → [Screen B] → [WorkManager enqueue]
                     └─ Error   → [Snackbar] → [Retry]
```

## Detailed Steps

### Step 1: [Action]
**Screen**: [Composable name]
**User Action**: [Tap/input]
**System Response**: ViewModel call, use case, repository action
**UI Elements**: Buttons, text fields, dialogs
**Data Flow**: Ktor request, Room write, WorkManager schedule
**Next Step**: → Step 2

## Success Scenarios
1. **[Scenario]**: Expected state, nav destination, background work

## Error Scenarios
1. **[Error]**: Failure mode, message, offline fallback, worker retry

## Exit Points
1. **Success**: Destination or event emitted
2. **Cancel**: Backstack action or process
3. **Error**: Persisted state, event log, telemetry

## State Diagram (Optional)
- Visual map of UI + WorkManager/Repository states.
```

### Example User Flow

**File**: `docs/stories/auth/user-flows/login-flow.md`

```markdown
# Login Flow - User Flow

## Overview
User enters credentials, iotoms validates them, and bootstraps the device session.

## Entry Points
1. **App Launch**: `MainActivity` routes to `Login` destination if no session.
2. **Session Timeout**: `SessionExpiredWorker` navigates user back to login.

## Flow Diagram
```
[Splash] → [Login Screen] → [Validate Form] → [Call RegisterUseCase]
                                                     ├─ Success → [Persist Session] → [Navigate to Home]
                                                     └─ Error   → [Show FormError] → [Stay on Login]
```

## Detailed Steps

### Step 1: App Launch
**Screen**: `LoginScreen`
**User Action**: Fill `username`, `password`, `domain`, `registerId`
**System Response**: `LoginViewModel` validates fields and exposes `LoginUiState`
**UI Elements**: TextFields, PasswordField, Domain drop-down, CTA
**Data Flow**: No network yet, local validation
**Next Step**: → Step 2

### Step 2: Submit Credentials
**Screen**: `LoginScreen`
**User Action**: Tap `Sign In`
**System Response**: ViewModel triggers `RegisterUseCase`, transitions to `Loading`
**UI Elements**: Progress indicator overlay
**Data Flow**: Ktor call via `AuthenticationRepository`, `Result` returned
**Next Step**: → Step 3 (result handling)

### Step 3: Handle Result
**Success**: Persist tokens, move to auth-protected destination
**Error**: Show inline form errors or snackbar, allow retry

## Success Scenarios
1. **Fresh Login**: Valid credentials, immediate nav to Home
2. **Auto Retry**: Network hiccup, WorkManager retries request, user informed

## Error Scenarios
1. **Validation Failure**: Inline errors highlight offending TextField
2. **API Failure**: Message from `ApiError`, offline fallback if cached session exists

## Exit Points
1. **Success**: `NavDisplay` pops login and pushes first home destination
2. **Cancel**: Back closes app or returns to calling flow
3. **Error**: Stays on login with error state, metrics logged
```

---

## Developer Notes Maintenance Guidelines

### When to Update `docs/DEV_NOTES.md`

Update (or create) the developer notes immediately when:

1. **Architecture Changes**
   - New feature modules, navigation graphs, or window layouts
   - Room schema updates, DAO changes, migrations
   - Koin module restructuring, WorkManager configurations
2. **Development Standards Change**
   - New Compose patterns, animation libraries, or lint rules
   - Gradle plugin upgrades, Kotlin language level changes
   - Testing methodologies (Snapshot tests, Robolectric adoption)
3. **User Preferences / Stakeholder Feedback**
   - Product expectations on accessibility, error messaging, or analytics
   - Preferred communication cadence and demo expectations
4. **Major Milestones**
   - Feature toggles enabled, milestone releases, beta rollouts
   - Backend contract updates impacting Android consumers

### Required Sections in `docs/DEV_NOTES.md`

#### 1. Overview
```markdown
# Overview

Iotoms Android is a Jetpack Compose application for [business purpose].
It targets SDK 24–36, supports phones/tablets, and relies on Ktor, Room, WorkManager.
```

#### 2. Recent Changes
```markdown
# Recent Changes

**[Month Year]:**
- [Change]: Short description
- [API Update]: Impact summary
```

#### 3. Preferences & Standards
```markdown
# Preferences & Standards

Communication style, code review focus, testing depth, release cadence.
```

#### 4. System Architecture
```markdown
# System Architecture

## UI
Compose + Navigation 3 + Material 3 guidelines.

## Domain
Use cases, repositories, error mapping, background sync.

## Data
Ktor clients, Room DB, Worker queue, serialization strategy.

## Development Standards
Naming conventions, formatting, lint, CI commands.
```

#### 5. External Dependencies
```markdown
# External Dependencies

## Core Framework
- **Jetpack Compose**: Declarative UI
- **Ktor Client**: Networking
- **Room**: Persistence
- **Koin**: Dependency injection
- **WorkManager**: Background tasks
```

### Update Checklist

Before marking a story as completed:
- [ ] Update `docs/DEV_NOTES.md` Recent Changes.
- [ ] Add/adjust standards or tooling decisions.
- [ ] Note schema/API migrations and app module impacts.
- [ ] Capture new dependencies/libraries.

---

## Feature Development Workflow

### Complete Feature Workflow

```
1. PLAN
   ├─ Gather business need & success metric
   ├─ Map Android user flows
   ├─ Break into BMAD stories
   └─ Prioritize & size

2. DOCUMENT
   ├─ Create feature folder + stories
   ├─ Write acceptance criteria
   ├─ Document user flows & diagrams
   └─ Secure approvals

3. SETUP
   ├─ Create data/domain/ui packages
   ├─ Wire DI modules & navigation
   ├─ Define API endpoints, Room schema, workers
   └─ Configure resources/permissions

4. IMPLEMENT
   ├─ Build Compose components/screens
   ├─ Hook ViewModels + use cases + repositories
   ├─ Integrate Ktor/Room/WorkManager
   └─ Handle errors, offline, accessibility

5. TEST
   ├─ Unit tests (`./gradlew testDebugUnitTest`)
   ├─ Compose UI tests (`./gradlew connectedDebugAndroidTest`)
   ├─ Manual QA across devices/states
   └─ Verify acceptance & edge cases

6. DOCUMENT & DEPLOY
   ├─ Update story status + DEV_NOTES
   ├─ Capture learnings & deviations
   ├─ Prepare release notes / telemetry flags
   └─ Merge & tag release builds
```

### Story Status Workflow

```
Planned → In Progress → Review → Completed
                    ↓
                 Blocked
                    ↓
              (resolve) → In Progress
```

---

## Templates

### 1. Feature README Template

**File**: `docs/stories/[feature-name]/README.md`

```markdown
# [Feature Name]

## Overview
[Purpose and high-level behavior]

## Business Context
[Problem being solved]

## User Value
[Value to Android end users]

## Stories Overview

| Story ID | Title | Priority | Status | Description |
|----------|-------|----------|--------|-------------|
| [ID] | [Title] | [Priority] | [Status] | [What it covers] |

## User Flows
- [Flow](./user-flows/[flow-name].md): Description

## Implementation Status
- [X] Story [ID] - [Title]
- [ ] Story [ID] - [Title]

## Technical Overview

### UI
- **Composable**: Purpose, navigation route

### Domain & Data
- **UseCase**: Business rule
- **Repository/DAO**: Data interactions
- **WorkManager**: Background tasks (if any)

### APIs Used
- `[endpoint]`: Request/response summary

### Dependencies
- [Dependency 1]

## Future Enhancements
- [Enhancement idea]
```

### 2. Individual Story Template

**File**: `docs/stories/[feature-name]/story-[ID]-[title-slug].md`

```markdown
# [Story Title]

**Story ID**: [FEATURE-CODE]-[NUMBER]
**Priority**: Critical | High | Medium | Low
**Status**: Planned | In Progress | Completed | Blocked

## Requirement
[Business outcome]

**User Value**: [What this enables]
**Business Impact**: [Why it matters]

## Design Context
[Compose references, Figma links, adaptive requirements]

**Design Assets**:
- [Links/screenshots]

## User Flow

### Entry Points
- [Route or trigger]

### Flow Steps
1. [Step]
   - User action:
   - System response:
   - UI state:

### Exit Points
- Success:
- Cancel:
- Error:

## Acceptance Criteria
1. **[Criterion 1]**: Description
2. **[Criterion 2]**: Description
3. **[Criterion 3]**: Description

## Testing Notes

### Test Scenarios
1. **Happy Path**: [Steps]
2. **Edge Cases**: [Scenarios]
3. **Error Handling**: [Scenarios]

### Manual Test Checklist
- [ ] [Check]

## Implementation Notes

### Technical Approach
[Patterns, state, DI]

### File Structure
```
app/src/main/java/com/iotoms/ui/[feature]/
```

### Key Components
- **Composable/ViewModel**: Purpose

### Dependencies
- [Dependency]

### Implementation Checklist
- [ ] [Task]

## Dependencies

### Blocked By
- [STORY-ID]

### Blocks
- [STORY-ID]

### Related Stories
- [STORY-ID]

## Notes
[Anything else]
```

### 3. User Flow Template

**File**: `docs/stories/[feature-name]/user-flows/[flow-name].md`

```markdown
# [Flow Name] - User Flow

## Overview
[Goal]

## Entry Points
1. **[Entry]**: Description

## Flow Diagram
```
[ASCII diagram]
```

## Detailed Steps

### Step 1: [Action]
**Screen**: [Composable]
**User Action**: [Action]
**System Response**: [ViewModel/use case]
**UI Elements**: [Elements]
**Data Flow**: [API/DB]
**Next Step**: → Step 2

## Success Scenarios
1. **[Scenario]**: Behavior

## Error Scenarios
1. **[Error]**: Handling

## Exit Points
1. **Success**: Destination
2. **Cancel**: Destination
3. **Error**: Destination/retry
```

---

## Story ID Conventions

### Feature Code Prefixes
- `REG` - Registration/Auth
- `BRD` - Branding
- `QPK` - QuickPick/Home
- `PRD` - Product Catalog
- `CART` - Cart
- `PAY` - Payment
- `CTO` - Configure-to-Order
- `SRC` - Search
- `CAT` - Category Navigation
- `USR` - User Management
- `ORD` - Order Management

### Numbering
- Use zero-padded 4-digit numbers (`0001`, `0002`, ...).
- Increment sequentially per feature.
- Example: `REG-0001`, `REG-0002`, `CART-0001`.

---

## Best Practices

### Story Writing
1. **Keep stories atomic**: One Android capability per story.
2. **Make criteria testable**: Link to practical Compose/UI verifications.
3. **Reference flows**: Always link to `user-flows` docs.
4. **Explain decisions**: Document DI bindings, navigation impacts, and caching choices.
5. **Keep artifacts synced**: Update story + README when changes occur.

### User Flows
1. **Entry first**: Document navigation routes, intents, or background entry.
2. **Map states**: Loading, success, error, offline, permissions.
3. **Show branches**: Compose dialog choices, worker retries.
4. **Include system actions**: API calls, Room reads/writes, WorkManager.
5. **Define exits**: Destination, process endings, metrics.

### Documentation
1. **Write during implementation**: Do not leave docs for later.
2. **Stay current**: Update with every commit if behavior changes.
3. **Link artifacts**: Stories ↔ PRs ↔ flows ↔ code.
4. **Use examples**: Screenshots, Compose previews, logs.
5. **Review regularly**: Schedule doc reviews during sprint reviews.

### Implementation
1. **Follow layering**: Keep `data`, `domain`, `ui` boundaries intact.
2. **Use DI**: Inject everything via Koin modules (`di/` files).
3. **Leverage coroutines + Flow**: For state and background work.
4. **Handle offline**: Cache responses, design offline UX, queue WorkManager jobs.
5. **Test continuously**: Unit, instrumentation, snapshot, and manual QA.

---

## Appendix: Example Stories

Use existing implementations as references:
- Auth/Login: `app/src/main/java/com/iotoms/ui/auth/login/`
- Cart module scaffolding: `app/src/main/java/com/iotoms/ui/cart/`
- Customer flows: `app/src/main/java/com/iotoms/ui/customer/`
- DI wiring: `app/src/main/java/com/iotoms/di/`

---

**Document Version**: 1.0  
**Last Updated**: March 2, 2026  
**Maintained By**: Android Development Team
