# Design: Move JVM-specific arbs to commonMain

**Date:** 2026-05-06
**Status:** Approved

## Goal

Move every arb that currently lives in `src/jvmMain/` into `src/commonMain/` so all
arbs are usable from every Kotlin target this multiplatform project compiles for
(currently JVM and JS, browser + nodejs). Replace JVM-only resource loading and
JVM-only types with multiplatform equivalents.

## Approach

Resource files become **compile-time-generated Kotlin constants** in `commonMain`.
No runtime IO, no `expect`/`actual`, no `kotlinx-io`, no `univocity-parsers`.

A `buildSrc` Gradle task reads `src/commonMain/resources/**` at build time and
generates Kotlin source files declaring the data as plain `val` constants. The
arbs read from those constants at runtime — every target gets the same data with
zero runtime IO.

## Files moving from `jvmMain` → `commonMain`

- `names.kt`
- `colors.kt`
- `domains.kt`
- `products/brands.kt`
- `products/gpc.kt`
- `products/products.kt`
- `tube/tube.kt`
- `wine/wine.kt`
- `movies/harrypotter.kt`

## Files deleted

- `loader.kt` — no more `loadResource` / `loadResourceAsLines`.

## Files staying in `jvmMain`

- `run.kt` — a `main` function used for manual runs. Not part of the public arb
  surface, no need to make it multiplatform.

## Resource file layout

Move `src/jvmMain/resources/**` → `src/commonMain/resources/**` as the single
source of truth. They are inputs to the generator; they no longer need to be on
the JVM classpath at runtime, but keeping them under `commonMain/resources` is
harmless and discoverable.

Files moving:

- `brands.txt`, `colors.txt`, `country_tlds.txt`, `first_names.txt`,
  `last_names.txt`, `gpc.txt`
- `harrypotter_first_names.csv`, `harrypotter_last_names.csv` (treated as
  one-value-per-line, like `.txt`)
- `tube/stations.csv`
- `wine/country.txt`, `wine/region.txt`, `wine/variety.txt`,
  `wine/vineyards.txt`, `wine/winery.txt`

## Build-time generator (`buildSrc`)

A Gradle task `generateArbResources`:

- **Inputs:** `src/commonMain/resources/**`.
- **Outputs:** `build/generated/sources/arbs-data/commonMain/kotlin/io/kotest/property/arbs/generated/**`.
- **For each `*.txt`** → a Kotlin file declaring
  `internal val <camelCaseName>: List<String> = listOf("…", "…")`. Strings are
  escaped for `\\`, `"`, and `$`.
- **For `tube/stations.csv`** → a Kotlin file declaring
  `internal val tubeStationsData: List<Station> = listOf(Station(…), …)`. The
  generator parses the CSV at build time (it runs in Gradle — plain `java.io`
  is fine there) and emits constructor calls. The `Station` data class lives in
  `commonMain` as today.
- **For `harrypotter_first_names.csv` / `harrypotter_last_names.csv`** → treated
  like `.txt` (one value per line; matches current code's behaviour).
- **Naming:** generated `val` names are `camelCase` of the file stem with a
  `Data` suffix, applied uniformly to both `.txt` and line-per-value `.csv`
  inputs. Examples: `first_names.txt` → `firstNamesData`,
  `harrypotter_first_names.csv` → `harrypotterFirstNamesData`,
  `tube/stations.csv` → `tubeStationsData`,
  `wine/region.txt` → `wineRegionData` (the parent directory disambiguates by
  appearing in the package — see below).
- **Package:** every generated file goes in
  `io.kotest.property.arbs.generated`. Subdirectories under `resources/` map to
  sub-packages: `wine/region.txt` → `io.kotest.property.arbs.generated.wine`,
  `tube/stations.csv` → `io.kotest.property.arbs.generated.tube`.
- **Wired in `build.gradle.kts`:**
  ```kotlin
  kotlin.sourceSets["commonMain"].kotlin.srcDir(
      tasks.named("generateArbResources").map { it.outputs.files }
  )
  ```
  The task runs before `compileKotlinCommon`.

## Code changes

### Resource access pattern

Each arb file replaces:

```kotlin
private val foo = lazy { loadResourceAsLines("/foo.txt") }
```

with an import of the generated constant:

```kotlin
import io.kotest.property.arbs.generated.fooData
```

The `lazy { }` wrapper is no longer useful — referencing a top-level `val`
constant is cheap. Drop it. Call sites change `foo.value` → `fooData`.

### `tube.kt`

- `java.time.LocalDateTime` → `kotlinx.datetime.LocalDateTime`.
- Date arithmetic (`minusDays`, `minusSeconds`) is rebuilt via
  `Instant`/`DateTimePeriod`/`DateTimeUnit` round-trip in `TimeZone.UTC`:
  ```kotlin
  val base = LocalDateTime(2020, 12, 31, 23, 59, 59)
      .toInstant(TimeZone.UTC)
  val instant = base
      .minus(Random.nextLong(365 * 20), DateTimeUnit.DAY, TimeZone.UTC)
      .minus(Random.nextLong(60 * 60 * 24), DateTimeUnit.SECOND)
  val date = instant.toLocalDateTime(TimeZone.UTC)
  ```
- `Station` list comes from the generated `tubeStationsData` constant; the CSV
  parsing block (and `CsvParser` / `CsvParserSettings`) is removed.

### Other arb files

`harrypotter.kt`, `wine.kt`, `colors.kt`, `domains.kt`, `names.kt`,
`products/brands.kt`, `products/gpc.kt` — only their imports change (point at
the generated constants instead of `loadResourceAsLines`); function bodies are
unchanged.

`products.kt` — no resource access; just moves location.

## Dependency changes

### `build.gradle.kts` + `gradle/libs.versions.toml`

- **Remove** the runtime dependency on `univocity-parsers`. Drop it from
  `commonMain.dependencies` and from `libs.versions.toml`.
- **Keep** `kotlinx-datetime` (already a `commonMain` dependency, now actually
  exercised at runtime by `tube.kt`).
- **Keep** Kotlin 1.6.21 — no toolchain bump required.
- **No new runtime dependencies.**

The generator in `buildSrc` may use `univocity-parsers` internally if it
simplifies CSV reading at build time, but that's a `buildSrc` dependency only —
it does not propagate to the published artefact.

## Public API

No changes. Data classes, function signatures, package paths are identical.
The change is internal: where the data comes from. Existing consumers continue
to work without modification.

## Tests

- Existing tests (`StockExchangeTest`, `DomainTest`, `BrandTest`,
  `GoogleTaxonomyTest`, `ChessTest`) stay in `jvmTest`. They must keep passing
  against the moved arbs.
- Add one smoke test in `commonTest` exercising an arb that was previously
  JVM-only (e.g. `Arb.firstName()`, `Arb.tubeStation()`) to prove the data is
  reachable from common code on every target. This requires creating
  `src/commonTest/kotlin/...` and a `commonTest` source set in
  `build.gradle.kts` with the multiplatform Kotest engine dependency
  (artifact name to be confirmed against Kotest 5.5.5 — likely
  `io.kotest:kotest-framework-engine`). If wiring `commonTest` proves
  disproportionate, fall back to a `jsTest` smoke test alongside the existing
  `jvmTest` to confirm the data compiles and is reachable on JS.

## Verification

- `./gradlew build` succeeds (compiles `commonMain`, `jvmMain`, `jsMain`).
- `./gradlew jvmTest` — all existing JVM tests pass.
- `./gradlew jsTest` (or `jsBrowserTest` / `jsNodeTest` per existing config) —
  at minimum, JS compilation succeeds; the new `commonTest` smoke test runs on
  JS.
- Manual run: `Arb.usernames().take(100)` from `run.kt` still works from JVM.
- Inspect the generated sources under `build/generated/sources/arbs-data/` and
  confirm they contain the expected constants.

## Out of scope

- Bumping Kotlin / Gradle versions.
- Adding any new arbs or new resource files.
- Changing the public API.
- Migrating `run.kt` to multiplatform.
- Adding new platform targets (Native, WASM, etc.).
