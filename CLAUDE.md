# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build & test commands

Use the Gradle wrapper (`./gradlew`) from the repo root. Single `:app` module.

- Assemble debug APK: `./gradlew :app:assembleDebug`
- Install on connected device/emulator: `./gradlew :app:installDebug`
- Unit tests (JVM): `./gradlew :app:testDebugUnitTest`
- Single unit test class: `./gradlew :app:testDebugUnitTest --tests "com.eyecon.glo.ExampleUnitTest"`
- Instrumented tests (require device/emulator): `./gradlew :app:connectedDebugAndroidTest`
- Lint: `./gradlew :app:lintDebug` (report under `app/build/reports/lint-results-debug.html`)
- Clean: `./gradlew clean`

`local.properties` (gitignored) must point `sdk.dir` at the local Android SDK.

## Architecture

Single-module Android app built with Jetpack Compose + Material3. Kotlin 2.2.10, AGP 9.1.1, Compose BOM 2026.02.01. `compileSdk` 36, `minSdk` 28, Java 11 source/target.

Naming gotcha: the Gradle project is `Traffiq Sense` and the theme is `TraffiqSenseTheme`, but the Android `namespace` and `applicationId` are `com.eyecon.glo` — all Kotlin sources live under `app/src/main/java/com/eyecon/glo/`. Keep new code under that package; don't rename to match the project name without an explicit ask.

Entry points (`AndroidManifest.xml`):
- `LoadingActivity` — `exported="true"` with the `MAIN`/`LAUNCHER` intent filter. This is the actual launch activity.
- `MainActivity` — `exported="false"`, not currently reached from any intent filter.

Both activities are empty Compose shells (`enableEdgeToEdge()` + empty `setContent {}`). When wiring real UI, drive it from `LoadingActivity` first since that's what the launcher opens, and wrap composables in `TraffiqSenseTheme { ... }` (defined in `ui/theme/Theme.kt`, supports dynamic color on Android 12+).

Dependencies are managed via the version catalog at `gradle/libs.versions.toml` — add libraries there rather than hard-coding versions in `app/build.gradle.kts`.
