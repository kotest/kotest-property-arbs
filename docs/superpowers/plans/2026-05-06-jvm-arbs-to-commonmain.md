# Move JVM-specific arbs to commonMain — Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** Move every arb file from `src/jvmMain/` into `src/commonMain/` so all arbs are usable from JVM and JS targets, replacing JVM-only resource loading and JVM-only types with multiplatform equivalents.

**Architecture:** A `buildSrc` Gradle task reads resource files from `src/commonMain/resources/**` at build time and generates Kotlin source files declaring the data as `internal val` constants. Arbs read those constants directly — zero runtime IO, no `expect`/`actual`, no `kotlinx-io`, no `univocity-parsers` at runtime. `tube.kt` swaps `java.time.LocalDateTime` for `kotlinx.datetime.LocalDateTime` (already a dependency).

**Tech Stack:** Kotlin 1.6.21 (unchanged), Gradle 7.6.6 (unchanged), Kotlin Multiplatform with JVM and JS targets, `kotlinx-datetime` 0.4.0, Kotest 5.5.5 (jvmTest only); `univocity-parsers` 2.9.1 used inside `buildSrc` only after migration.

**Reference:** Spec at `docs/superpowers/specs/2026-05-06-jvm-arbs-to-commonmain-design.md`.

---

## File Structure

### New files (created by this plan)

| Path | Responsibility |
|---|---|
| `buildSrc/build.gradle.kts` | Declare buildSrc as a Kotlin DSL project; depend on `univocity-parsers` for build-time CSV parsing; wire JUnit for generator unit tests. |
| `buildSrc/src/main/kotlin/io/kotest/property/arbs/build/ArbsResourceGenerator.kt` | Pure function (no Gradle imports) that walks a resources directory and writes generated Kotlin source files into an output directory. |
| `buildSrc/src/main/kotlin/io/kotest/property/arbs/build/GenerateArbResourcesTask.kt` | A Gradle `DefaultTask` wrapping the generator with `@InputDirectory` / `@OutputDirectory` for incremental builds. |
| `buildSrc/src/test/kotlin/io/kotest/property/arbs/build/ArbsResourceGeneratorTest.kt` | Unit tests for the generator: line files, escaping, package mapping, stations CSV. |
| `src/jsTest/kotlin/io/kotest/property/arbs/GeneratedDataSmokeTest.kt` | JS smoke test verifying arbs that use generated data are reachable on JS. |

### Files moved (`git mv`)

All files preserve their package declarations.

| From | To |
|---|---|
| `src/jvmMain/kotlin/io/kotest/property/arbs/names.kt` | `src/commonMain/kotlin/io/kotest/property/arbs/names.kt` |
| `src/jvmMain/kotlin/io/kotest/property/arbs/colors.kt` | `src/commonMain/kotlin/io/kotest/property/arbs/colors.kt` |
| `src/jvmMain/kotlin/io/kotest/property/arbs/domains.kt` | `src/commonMain/kotlin/io/kotest/property/arbs/domains.kt` |
| `src/jvmMain/kotlin/io/kotest/property/arbs/products/brands.kt` | `src/commonMain/kotlin/io/kotest/property/arbs/products/brands.kt` |
| `src/jvmMain/kotlin/io/kotest/property/arbs/products/gpc.kt` | `src/commonMain/kotlin/io/kotest/property/arbs/products/gpc.kt` |
| `src/jvmMain/kotlin/io/kotest/property/arbs/products/products.kt` | `src/commonMain/kotlin/io/kotest/property/arbs/products/products.kt` |
| `src/jvmMain/kotlin/io/kotest/property/arbs/wine/wine.kt` | `src/commonMain/kotlin/io/kotest/property/arbs/wine/wine.kt` |
| `src/jvmMain/kotlin/io/kotest/property/arbs/movies/harrypotter.kt` | `src/commonMain/kotlin/io/kotest/property/arbs/movies/harrypotter.kt` |
| `src/jvmMain/kotlin/io/kotest/property/arbs/tube/tube.kt` | `src/commonMain/kotlin/io/kotest/property/arbs/tube/tube.kt` |
| `src/jvmMain/resources/**` | `src/commonMain/resources/**` (all `.txt` and `.csv` files, preserving subdirectory layout) |

### Files deleted

| Path | Why |
|---|---|
| `src/jvmMain/kotlin/io/kotest/property/arbs/loader.kt` | No longer needed — runtime resource loading is gone. |

### Files modified

| Path | Change |
|---|---|
| `build.gradle.kts` | Register the `generateArbResources` task; wire its output dir into `commonMain` Kotlin source set; add `jsTest` source set with `kotlin.test` dep; remove `univocity-parsers` from `commonMain` deps. |
| `gradle/libs.versions.toml` | Remove `univocity-parsers` library entry (it now lives only in `buildSrc/build.gradle.kts`). |
| Each moved arb file | Replace `loadResourceAsLines("/foo.txt")` with imported generated constant; drop `lazy { }` wrappers; tube also swaps `java.time` for `kotlinx-datetime`. |

### Generated files (build output, not committed)

`build/generated/sources/arbs-data/commonMain/kotlin/io/kotest/property/arbs/generated/**` — one Kotlin file per resource file. Naming convention:

| Resource file | Generated package | Generated `val` |
|---|---|---|
| `first_names.txt` | `io.kotest.property.arbs.generated` | `firstNamesData: List<String>` |
| `last_names.txt` | `io.kotest.property.arbs.generated` | `lastNamesData: List<String>` |
| `colors.txt` | `io.kotest.property.arbs.generated` | `colorsData: List<String>` |
| `country_tlds.txt` | `io.kotest.property.arbs.generated` | `countryTldsData: List<String>` |
| `brands.txt` | `io.kotest.property.arbs.generated` | `brandsData: List<String>` |
| `gpc.txt` | `io.kotest.property.arbs.generated` | `gpcData: List<String>` |
| `harrypotter_first_names.csv` | `io.kotest.property.arbs.generated` | `harrypotterFirstNamesData: List<String>` |
| `harrypotter_last_names.csv` | `io.kotest.property.arbs.generated` | `harrypotterLastNamesData: List<String>` |
| `wine/country.txt` | `io.kotest.property.arbs.generated.wine` | `wineCountryData: List<String>` |
| `wine/region.txt` | `io.kotest.property.arbs.generated.wine` | `wineRegionData: List<String>` |
| `wine/variety.txt` | `io.kotest.property.arbs.generated.wine` | `wineVarietyData: List<String>` |
| `wine/vineyards.txt` | `io.kotest.property.arbs.generated.wine` | `wineVineyardsData: List<String>` |
| `wine/winery.txt` | `io.kotest.property.arbs.generated.wine` | `wineWineryData: List<String>` |
| `tube/stations.csv` | `io.kotest.property.arbs.generated.tube` | `tubeStationsData: List<Station>` |

`val` naming rule: camelCase of `<parentDirParts><fileStem>` joined with no separator (e.g. `wine/region` → `wineRegion`), plus `Data` suffix. Underscores in the file stem split into camelCase parts (`first_names` → `firstNames`).

---

## Task 1: Set up `buildSrc` with `.txt` → `List<String>` generator (TDD)

**Files:**
- Create: `buildSrc/build.gradle.kts`
- Create: `buildSrc/settings.gradle.kts`
- Create: `buildSrc/src/main/kotlin/io/kotest/property/arbs/build/ArbsResourceGenerator.kt`
- Create: `buildSrc/src/test/kotlin/io/kotest/property/arbs/build/ArbsResourceGeneratorTest.kt`

- [ ] **Step 1: Create `buildSrc/settings.gradle.kts`**

```kotlin
rootProject.name = "buildSrc"
```

- [ ] **Step 2: Create `buildSrc/build.gradle.kts`**

```kotlin
plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.univocity:univocity-parsers:2.9.1")
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.0")
}

tasks.test {
    useJUnitPlatform()
}
```

- [ ] **Step 3: Verify `buildSrc` is picked up**

Run: `./gradlew help`
Expected: succeeds with no errors. (`buildSrc` is auto-discovered by Gradle.)

- [ ] **Step 4: Write failing test for line-file generation**

Create `buildSrc/src/test/kotlin/io/kotest/property/arbs/build/ArbsResourceGeneratorTest.kt`:

```kotlin
package io.kotest.property.arbs.build

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.io.File

class ArbsResourceGeneratorTest {

    @TempDir lateinit var tempDir: File

    @Test
    fun `txt file becomes a top-level List of String constant in the generated package`() {
        val resourcesRoot = File(tempDir, "resources").apply { mkdirs() }
        val outputRoot = File(tempDir, "out").apply { mkdirs() }
        File(resourcesRoot, "first_names.txt").writeText("John\nWilliam\nJames\n")

        ArbsResourceGenerator(resourcesRoot, outputRoot).generate()

        val generated = File(
            outputRoot,
            "io/kotest/property/arbs/generated/FirstNamesData.kt"
        )
        assertTrue(generated.exists(), "expected generated file at $generated")
        val text = generated.readText()
        assertTrue(text.contains("package io.kotest.property.arbs.generated"))
        assertTrue(text.contains("internal val firstNamesData: List<String> = listOf("))
        assertTrue(text.contains("\"John\""))
        assertTrue(text.contains("\"William\""))
        assertTrue(text.contains("\"James\""))
    }

    @Test
    fun `txt file in a subdirectory generates into the matching subpackage with prefixed val name`() {
        val resourcesRoot = File(tempDir, "resources").apply { mkdirs() }
        val outputRoot = File(tempDir, "out").apply { mkdirs() }
        File(resourcesRoot, "wine").mkdirs()
        File(resourcesRoot, "wine/region.txt").writeText("Bordeaux\nNapa\n")

        ArbsResourceGenerator(resourcesRoot, outputRoot).generate()

        val generated = File(
            outputRoot,
            "io/kotest/property/arbs/generated/wine/WineRegionData.kt"
        )
        assertTrue(generated.exists(), "expected generated file at $generated")
        val text = generated.readText()
        assertTrue(text.contains("package io.kotest.property.arbs.generated.wine"))
        assertTrue(text.contains("internal val wineRegionData: List<String> = listOf("))
    }

    @Test
    fun `lines containing quotes backslashes and dollar signs are escaped`() {
        val resourcesRoot = File(tempDir, "resources").apply { mkdirs() }
        val outputRoot = File(tempDir, "out").apply { mkdirs() }
        // Three input lines: one with quote, one with backslash, one with dollar.
        val input = listOf(
            "she said \"hi\"",
            "back\\slash",
            "\$money"
        ).joinToString("\n", postfix = "\n")
        File(resourcesRoot, "tricky.txt").writeText(input)

        ArbsResourceGenerator(resourcesRoot, outputRoot).generate()

        val text = File(outputRoot, "io/kotest/property/arbs/generated/TrickyData.kt").readText()
        // The generated file's bytes should contain escaped versions:
        //   quote     → backslash + quote          → in Kotlin literal: "\\\""
        //   backslash → backslash + backslash      → in Kotlin literal: "\\\\"
        //   dollar    → backslash + dollar         → in Kotlin literal: "\\\$"
        assertTrue(text.contains("she said \\\"hi\\\""), "expected escaped quotes in: $text")
        assertTrue(text.contains("back\\\\slash"), "expected escaped backslash in: $text")
        assertTrue(text.contains("\\\$money"), "expected escaped dollar in: $text")
    }

    @Test
    fun `line order in generated file matches input file order exactly`() {
        val resourcesRoot = File(tempDir, "resources").apply { mkdirs() }
        val outputRoot = File(tempDir, "out").apply { mkdirs() }
        File(resourcesRoot, "ordered.txt").writeText("first\nsecond\nthird\n")

        ArbsResourceGenerator(resourcesRoot, outputRoot).generate()

        val text = File(outputRoot, "io/kotest/property/arbs/generated/OrderedData.kt").readText()
        val firstIdx = text.indexOf("\"first\"")
        val secondIdx = text.indexOf("\"second\"")
        val thirdIdx = text.indexOf("\"third\"")
        assertTrue(firstIdx in 0 until secondIdx)
        assertTrue(secondIdx in 0 until thirdIdx)
    }
}
```

- [ ] **Step 5: Run tests to verify they fail (no generator class yet)**

Run: `./gradlew :buildSrc:test`
Expected: FAIL with compilation error — `ArbsResourceGenerator` is unresolved.

- [ ] **Step 6: Create `ArbsResourceGenerator.kt`**

Create `buildSrc/src/main/kotlin/io/kotest/property/arbs/build/ArbsResourceGenerator.kt`:

```kotlin
package io.kotest.property.arbs.build

import java.io.File

class ArbsResourceGenerator(
    private val resourcesRoot: File,
    private val outputRoot: File,
) {

    fun generate() {
        if (!resourcesRoot.exists()) return
        outputRoot.mkdirs()
        resourcesRoot.walkTopDown()
            .filter { it.isFile }
            .forEach { generateForFile(it) }
    }

    private fun generateForFile(file: File) {
        val rel = file.relativeTo(resourcesRoot)
        val parentParts: List<String> = rel.parentFile
            ?.invariantSeparatorsPath
            ?.takeIf { it.isNotEmpty() && it != "." }
            ?.split('/')
            ?: emptyList()

        val pkg = (listOf("io", "kotest", "property", "arbs", "generated") + parentParts)
            .joinToString(".")
        val outDir = File(outputRoot, pkg.replace('.', '/'))
        outDir.mkdirs()

        val nameParts = parentParts + file.nameWithoutExtension
        val valName = toCamelCase(nameParts) + "Data"
        val fileName = capitalizeFirst(valName) + ".kt"

        val isTubeStations = rel.invariantSeparatorsPath == "tube/stations.csv"
        val text = if (isTubeStations) {
            renderTubeStations(file, pkg)
        } else {
            renderLines(file, pkg, valName)
        }

        File(outDir, fileName).writeText(text)
    }

    private fun renderLines(file: File, pkg: String, valName: String): String {
        val lines = file.readLines()
        return buildString {
            appendLine("// Generated by ArbsResourceGenerator. Do not edit by hand.")
            appendLine("package $pkg")
            appendLine()
            appendLine("internal val $valName: List<String> = listOf(")
            for (line in lines) {
                append("  \"")
                append(escapeKotlinString(line))
                appendLine("\",")
            }
            appendLine(")")
        }
    }

    /** Stub — implemented in Task 2. */
    private fun renderTubeStations(file: File, pkg: String): String {
        TODO("Implemented in Task 2")
    }

    private fun escapeKotlinString(s: String): String =
        s.replace("\\", "\\\\")
            .replace("\"", "\\\"")
            .replace("\$", "\\\$")

    /**
     * Joins parts into camelCase. Underscores inside any part are also treated as word breaks.
     * Examples: ["first_names"] → "firstNames"; ["wine", "region"] → "wineRegion".
     */
    private fun toCamelCase(parts: List<String>): String {
        val words = parts.flatMap { it.split('_') }.filter { it.isNotEmpty() }
        return buildString {
            words.forEachIndexed { idx, word ->
                if (idx == 0) append(word.replaceFirstChar { it.lowercase() })
                else append(word.replaceFirstChar { it.uppercase() })
            }
        }
    }

    private fun capitalizeFirst(s: String): String =
        s.replaceFirstChar { it.uppercase() }
}
```

- [ ] **Step 7: Run tests to verify they pass**

Run: `./gradlew :buildSrc:test`
Expected: PASS — all four tests green.

- [ ] **Step 8: Commit**

```bash
git add buildSrc
git commit -m "Add buildSrc with ArbsResourceGenerator for txt files

Generator walks a resources directory and emits Kotlin source files
declaring each .txt file as 'internal val <name>Data: List<String>'.
Subdirectories map to sub-packages of io.kotest.property.arbs.generated.
Strings are escaped for backslash, quote, and dollar.

Stub for tube/stations.csv lands in Task 2.

Co-Authored-By: Claude Opus 4.7 (1M context) <noreply@anthropic.com>"
```

---

## Task 2: Generator support for `tube/stations.csv` → `List<Station>` (TDD)

**Files:**
- Modify: `buildSrc/src/main/kotlin/io/kotest/property/arbs/build/ArbsResourceGenerator.kt`
- Modify: `buildSrc/src/test/kotlin/io/kotest/property/arbs/build/ArbsResourceGeneratorTest.kt`

- [ ] **Step 1: Add a failing test for stations CSV parsing**

Append to `ArbsResourceGeneratorTest.kt`:

```kotlin
@Test
fun `tube stations csv generates List of Station with correct fields and order`() {
    val resourcesRoot = File(tempDir, "resources").apply { mkdirs() }
    val outputRoot = File(tempDir, "out").apply { mkdirs() }
    File(resourcesRoot, "tube").mkdirs()
    File(resourcesRoot, "tube/stations.csv").writeText(
        """
        "id","latitude","longitude","name","display_name","zone","total_lines","rail"
        1,51.5028,-0.2801,"Acton Town","Acton<br />Town",3,2,0
        2,51.5143,-0.0755,"Aldgate",NULL,1,2,0
        """.trimIndent() + "\n"
    )

    ArbsResourceGenerator(resourcesRoot, outputRoot).generate()

    val generated = File(
        outputRoot,
        "io/kotest/property/arbs/generated/tube/TubeStationsData.kt"
    )
    assertTrue(generated.exists(), "expected generated file at $generated")
    val text = generated.readText()
    assertTrue(text.contains("package io.kotest.property.arbs.generated.tube"))
    assertTrue(text.contains("import io.kotest.property.arbs.tube.Station"))
    assertTrue(text.contains("internal val tubeStationsData: List<Station> = listOf("))
    // First record, in column order id, latitude, longitude, name, zone, lines, rail.
    assertTrue(
        text.contains("Station(1L, 51.5028, -0.2801, \"Acton Town\", 3.0, 2, 0)"),
        "first row missing or wrong; got:\n$text"
    )
    assertTrue(
        text.contains("Station(2L, 51.5143, -0.0755, \"Aldgate\", 1.0, 2, 0)"),
        "second row missing or wrong; got:\n$text"
    )
    // Order preserved.
    assertTrue(text.indexOf("Acton Town") < text.indexOf("Aldgate"))
}
```

- [ ] **Step 2: Run test, verify it fails**

Run: `./gradlew :buildSrc:test --tests "*tube stations*"`
Expected: FAIL — generator throws `NotImplementedError` from the `TODO` stub.

- [ ] **Step 3: Implement `renderTubeStations`**

Replace the stub `renderTubeStations` in `ArbsResourceGenerator.kt` with:

```kotlin
private fun renderTubeStations(file: File, pkg: String): String {
    val settings = com.univocity.parsers.csv.CsvParserSettings().apply {
        isHeaderExtractionEnabled = true
    }
    val records = com.univocity.parsers.csv.CsvParser(settings).parseAllRecords(file)
    return buildString {
        appendLine("// Generated by ArbsResourceGenerator. Do not edit by hand.")
        appendLine("package $pkg")
        appendLine()
        appendLine("import io.kotest.property.arbs.tube.Station")
        appendLine()
        appendLine("internal val tubeStationsData: List<Station> = listOf(")
        for (r in records) {
            append("  Station(")
            append(r.getLong("id")); append("L, ")
            append(r.getDouble("latitude")); append(", ")
            append(r.getDouble("longitude")); append(", ")
            append('"'); append(escapeKotlinString(r.getString("name"))); append("\", ")
            append(r.getDouble("zone")); append(", ")
            append(r.getInt("total_lines")); append(", ")
            append(r.getInt("rail"))
            appendLine("),")
        }
        appendLine(")")
    }
}
```

> Note on number formatting: `Long.toString()` produces e.g. `1`, no `L` suffix — that's why we append `"L"` manually. `Double.toString()` produces e.g. `3.0` for the value `3` — that yields `3.0` in the generated source, which is the correct `Double` literal. `Int.toString()` produces e.g. `0`. Verify locale-independence: these `toString()` calls use the JVM's default `Number.toString` which is locale-independent (always uses `.` decimal separator).

- [ ] **Step 4: Run all tests**

Run: `./gradlew :buildSrc:test`
Expected: PASS — all five tests green.

- [ ] **Step 5: Commit**

```bash
git add buildSrc
git commit -m "Generate List<Station> from tube/stations.csv at build time

The generator now special-cases tube/stations.csv, parsing it with
univocity-parsers (a buildSrc-only dependency) and emitting Station
constructor calls in column order: id, latitude, longitude, name,
zone, total_lines, rail. Imports io.kotest.property.arbs.tube.Station
since the generated file lives in the .generated.tube subpackage.

Co-Authored-By: Claude Opus 4.7 (1M context) <noreply@anthropic.com>"
```

---

## Task 3: Wrap the generator in a Gradle task and wire it into `commonMain`

**Files:**
- Create: `buildSrc/src/main/kotlin/io/kotest/property/arbs/build/GenerateArbResourcesTask.kt`
- Modify: `build.gradle.kts`

- [ ] **Step 1: Create the Gradle task class**

Create `buildSrc/src/main/kotlin/io/kotest/property/arbs/build/GenerateArbResourcesTask.kt`:

```kotlin
package io.kotest.property.arbs.build

import org.gradle.api.DefaultTask
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.PathSensitive
import org.gradle.api.tasks.PathSensitivity

abstract class GenerateArbResourcesTask : DefaultTask() {

    @get:InputDirectory
    @get:PathSensitive(PathSensitivity.RELATIVE)
    abstract val resourcesDir: DirectoryProperty

    @get:OutputDirectory
    abstract val outputDir: DirectoryProperty

    @TaskAction
    fun run() {
        val resources = resourcesDir.get().asFile
        val out = outputDir.get().asFile
        out.deleteRecursively()
        out.mkdirs()
        ArbsResourceGenerator(resources, out).generate()
    }
}
```

- [ ] **Step 2: Verify buildSrc still compiles**

Run: `./gradlew :buildSrc:compileKotlin`
Expected: PASS.

- [ ] **Step 3: Wire the task into `build.gradle.kts`**

Open `build.gradle.kts`. After the `kotlin { ... }` block (i.e., between `kotlin { ... }` and `tasks.withType<Test> { ... }`), add:

```kotlin
val generateArbResources = tasks.register<io.kotest.property.arbs.build.GenerateArbResourcesTask>("generateArbResources") {
    resourcesDir.set(layout.projectDirectory.dir("src/commonMain/resources"))
    outputDir.set(layout.buildDirectory.dir("generated/sources/arbs-data/commonMain/kotlin"))
}

kotlin.sourceSets.named("commonMain") {
    kotlin.srcDir(generateArbResources.map { it.outputDir })
}
```

> The `kotlin.srcDir(provider)` overload registers a Gradle `Provider` as a source dir. This implicitly sets `compileKotlinCommon` (and `compileKotlinJvm`, `compileKotlinJs`) to depend on `generateArbResources`.

- [ ] **Step 4: Create the `commonMain/resources` directory** (so the task's `@InputDirectory` exists)

Run:
```bash
mkdir -p src/commonMain/resources
# Add a placeholder so git tracks the directory; Task 4 will replace this.
touch src/commonMain/resources/.gitkeep
```

- [ ] **Step 5: Verify the task runs and produces no files (empty input)**

Run: `./gradlew generateArbResources`
Expected: PASS. `build/generated/sources/arbs-data/commonMain/kotlin/` exists; no `.kt` files inside (since input dir has only `.gitkeep`).

- [ ] **Step 6: Verify `compileKotlinCommonMainKotlinMetadata` (or equivalent) still succeeds**

Run: `./gradlew compileKotlinJvm`
Expected: PASS. (Generated source dir is empty, so it's a no-op for compilation; existing JVM code is untouched.)

- [ ] **Step 7: Commit**

```bash
git add buildSrc/src/main/kotlin/io/kotest/property/arbs/build/GenerateArbResourcesTask.kt build.gradle.kts src/commonMain/resources/.gitkeep
git commit -m "Wire GenerateArbResourcesTask into commonMain Kotlin source set

The task reads src/commonMain/resources and writes generated Kotlin
files into build/generated/sources/arbs-data/commonMain/kotlin, which
is registered as an additional commonMain source root. Kotlin
compile tasks depend on it transitively via the Provider wiring.

Co-Authored-By: Claude Opus 4.7 (1M context) <noreply@anthropic.com>"
```

---

## Task 4: Move resources from `jvmMain/resources` to `commonMain/resources`

**Files:**
- Move: every file under `src/jvmMain/resources/` → `src/commonMain/resources/`

- [ ] **Step 1: Confirm the resource files we're moving**

Run:
```bash
find src/jvmMain/resources -type f
```
Expected output (15 files):
```
src/jvmMain/resources/brands.txt
src/jvmMain/resources/colors.txt
src/jvmMain/resources/country_tlds.txt
src/jvmMain/resources/first_names.txt
src/jvmMain/resources/gpc.txt
src/jvmMain/resources/harrypotter_first_names.csv
src/jvmMain/resources/harrypotter_last_names.csv
src/jvmMain/resources/last_names.txt
src/jvmMain/resources/tube/stations.csv
src/jvmMain/resources/wine/country.txt
src/jvmMain/resources/wine/region.txt
src/jvmMain/resources/wine/variety.txt
src/jvmMain/resources/wine/vineyards.txt
src/jvmMain/resources/wine/winery.txt
src/jvmMain/resources/wine/variety.txt   # if duplicates, ignore — list above is the canonical set
```

- [ ] **Step 2: Remove the placeholder and `git mv` resources**

```bash
rm src/commonMain/resources/.gitkeep
mkdir -p src/commonMain/resources/tube src/commonMain/resources/wine
git mv src/jvmMain/resources/brands.txt              src/commonMain/resources/brands.txt
git mv src/jvmMain/resources/colors.txt              src/commonMain/resources/colors.txt
git mv src/jvmMain/resources/country_tlds.txt        src/commonMain/resources/country_tlds.txt
git mv src/jvmMain/resources/first_names.txt         src/commonMain/resources/first_names.txt
git mv src/jvmMain/resources/gpc.txt                 src/commonMain/resources/gpc.txt
git mv src/jvmMain/resources/harrypotter_first_names.csv src/commonMain/resources/harrypotter_first_names.csv
git mv src/jvmMain/resources/harrypotter_last_names.csv  src/commonMain/resources/harrypotter_last_names.csv
git mv src/jvmMain/resources/last_names.txt          src/commonMain/resources/last_names.txt
git mv src/jvmMain/resources/tube/stations.csv       src/commonMain/resources/tube/stations.csv
git mv src/jvmMain/resources/wine/country.txt        src/commonMain/resources/wine/country.txt
git mv src/jvmMain/resources/wine/region.txt         src/commonMain/resources/wine/region.txt
git mv src/jvmMain/resources/wine/variety.txt        src/commonMain/resources/wine/variety.txt
git mv src/jvmMain/resources/wine/vineyards.txt      src/commonMain/resources/wine/vineyards.txt
git mv src/jvmMain/resources/wine/winery.txt         src/commonMain/resources/wine/winery.txt
rmdir src/jvmMain/resources/tube src/jvmMain/resources/wine src/jvmMain/resources
```

- [ ] **Step 3: Run the generator and inspect output**

Run: `./gradlew generateArbResources`
Expected: PASS.

Verify output:
```bash
find build/generated/sources/arbs-data -name '*.kt' | sort
```
Expected (14 files):
```
build/generated/sources/arbs-data/commonMain/kotlin/io/kotest/property/arbs/generated/BrandsData.kt
build/generated/sources/arbs-data/commonMain/kotlin/io/kotest/property/arbs/generated/ColorsData.kt
build/generated/sources/arbs-data/commonMain/kotlin/io/kotest/property/arbs/generated/CountryTldsData.kt
build/generated/sources/arbs-data/commonMain/kotlin/io/kotest/property/arbs/generated/FirstNamesData.kt
build/generated/sources/arbs-data/commonMain/kotlin/io/kotest/property/arbs/generated/GpcData.kt
build/generated/sources/arbs-data/commonMain/kotlin/io/kotest/property/arbs/generated/HarrypotterFirstNamesData.kt
build/generated/sources/arbs-data/commonMain/kotlin/io/kotest/property/arbs/generated/HarrypotterLastNamesData.kt
build/generated/sources/arbs-data/commonMain/kotlin/io/kotest/property/arbs/generated/LastNamesData.kt
build/generated/sources/arbs-data/commonMain/kotlin/io/kotest/property/arbs/generated/tube/TubeStationsData.kt
build/generated/sources/arbs-data/commonMain/kotlin/io/kotest/property/arbs/generated/wine/WineCountryData.kt
build/generated/sources/arbs-data/commonMain/kotlin/io/kotest/property/arbs/generated/wine/WineRegionData.kt
build/generated/sources/arbs-data/commonMain/kotlin/io/kotest/property/arbs/generated/wine/WineVarietyData.kt
build/generated/sources/arbs-data/commonMain/kotlin/io/kotest/property/arbs/generated/wine/WineVineyardsData.kt
build/generated/sources/arbs-data/commonMain/kotlin/io/kotest/property/arbs/generated/wine/WineWineryData.kt
```

Spot check `head -20 build/generated/sources/arbs-data/commonMain/kotlin/io/kotest/property/arbs/generated/FirstNamesData.kt` — should look like:
```kotlin
// Generated by ArbsResourceGenerator. Do not edit by hand.
package io.kotest.property.arbs.generated

internal val firstNamesData: List<String> = listOf(
  "John",
  "William",
  ...
)
```

- [ ] **Step 4: Run jvmTest to confirm existing JVM tests still pass**

> Why this works: in Kotlin Multiplatform, the JVM target's runtime classpath includes resources from both `jvmMain` and `commonMain`. The existing `jvmMain` arb code calls `loadResourceAsLines("/first_names.txt")`, which uses `javaClass.getResourceAsStream` — that finds the file on the JVM classpath regardless of which source set provided it.

Run: `./gradlew jvmTest`
Expected: PASS — `BrandTest`, `DomainTest`, `StockExchangeTest`, `GoogleTaxonomyTest`, `ChessTest`, plus tests under `products/` and `games/`. Same green status as before this PR.

- [ ] **Step 5: Commit**

```bash
git add -A
git commit -m "Move resources from jvmMain/resources to commonMain/resources

Resources move under src/commonMain/resources so the buildSrc generator
can read them, and so they're delivered to all targets via the common
source set. JVM tests continue to pass because the JVM classpath
includes commonMain resources.

Co-Authored-By: Claude Opus 4.7 (1M context) <noreply@anthropic.com>"
```

---

## Task 5: Migrate `names.kt`, `colors.kt`, `domains.kt` to commonMain

**Files:**
- Move: `src/jvmMain/kotlin/io/kotest/property/arbs/names.kt` → `src/commonMain/kotlin/io/kotest/property/arbs/names.kt`
- Move: `src/jvmMain/kotlin/io/kotest/property/arbs/colors.kt` → `src/commonMain/kotlin/io/kotest/property/arbs/colors.kt`
- Move: `src/jvmMain/kotlin/io/kotest/property/arbs/domains.kt` → `src/commonMain/kotlin/io/kotest/property/arbs/domains.kt`

- [ ] **Step 1: Move the three files**

```bash
git mv src/jvmMain/kotlin/io/kotest/property/arbs/names.kt   src/commonMain/kotlin/io/kotest/property/arbs/names.kt
git mv src/jvmMain/kotlin/io/kotest/property/arbs/colors.kt  src/commonMain/kotlin/io/kotest/property/arbs/colors.kt
git mv src/jvmMain/kotlin/io/kotest/property/arbs/domains.kt src/commonMain/kotlin/io/kotest/property/arbs/domains.kt
```

- [ ] **Step 2: Replace `names.kt` body**

Overwrite `src/commonMain/kotlin/io/kotest/property/arbs/names.kt` with:

```kotlin
package io.kotest.property.arbs

import io.kotest.property.Arb
import io.kotest.property.arbitrary.arbitrary
import io.kotest.property.arbitrary.flatMap
import io.kotest.property.arbitrary.map
import io.kotest.property.arbs.generated.firstNamesData
import io.kotest.property.arbs.generated.lastNamesData

data class FirstName(val name: String)
data class LastName(val name: String)
data class Name(val first: FirstName, val last: LastName)

fun Arb.Companion.firstName() = arbitrary { FirstName(firstNamesData.random(it.random)) }
fun Arb.Companion.lastName() = arbitrary { LastName(lastNamesData.random(it.random)) }
fun Arb.Companion.name() = firstName().flatMap { first -> lastName().map { last -> Name(first, last) } }
```

- [ ] **Step 3: Replace `colors.kt` body**

Overwrite `src/commonMain/kotlin/io/kotest/property/arbs/colors.kt` with:

```kotlin
package io.kotest.property.arbs

import io.kotest.property.Arb
import io.kotest.property.arbitrary.map
import io.kotest.property.arbitrary.of
import io.kotest.property.arbs.generated.colorsData

data class Color(val value: String)

fun Arb.Companion.color() = Arb.of(colorsData).map { Color(it) }
```

- [ ] **Step 4: Replace `domains.kt` body**

Overwrite `src/commonMain/kotlin/io/kotest/property/arbs/domains.kt` with:

```kotlin
package io.kotest.property.arbs

import io.kotest.property.Arb
import io.kotest.property.arbitrary.arbitrary
import io.kotest.property.arbs.generated.countryTldsData

/**
 * Generates domains where the domain name is a random string with the given length,
 * and the tld is a real country level tld.
 *
 * Will randomly include www, cdn and www2 prefixes.
 */
fun Arb.Companion.domain(nameLength: IntRange = 3..20): Arb<Domain> = arbitrary { rs ->
  val prefix = prefixes.random(rs.random)
  val name = List(rs.random.nextInt(nameLength.first, nameLength.last)) { ('a'..'z').random(rs.random) }.joinToString("")
  val domain = listOfNotNull(prefix, name, countryTldsData.random(rs.random)).joinToString(".")
  Domain(domain)
}

private val prefixes = listOf("www", "www2", "cdn", null)

data class Domain(val value: String)
```

- [ ] **Step 5: Build to verify it compiles for both targets**

Run: `./gradlew compileKotlinJvm compileKotlinJs`
Expected: PASS.

- [ ] **Step 6: Run jvmTest to verify behavior is unchanged**

Run: `./gradlew jvmTest`
Expected: PASS. In particular `DomainTest` still asserts:
- seed 23523 → `Domain("cdn.bzfsaepnr.tj")`
- seed 8234 → `Domain("www.dwjjohdm.ad")`

These are deterministic on the seed and the contents/order of `country_tlds.txt`, both of which are preserved.

- [ ] **Step 7: Commit**

```bash
git add -A
git commit -m "Migrate names, colors, domains arbs to commonMain

Each replaces loadResourceAsLines + lazy with a direct reference to
the generated data constant. Public API and observable randomness are
unchanged: DomainTest, StockExchangeTest, etc. continue to pass on
the same seeds.

Co-Authored-By: Claude Opus 4.7 (1M context) <noreply@anthropic.com>"
```

---

## Task 6: Migrate `products/brands.kt`, `products/gpc.kt`, `products/products.kt`

**Files:**
- Move: three files from `jvmMain/kotlin/io/kotest/property/arbs/products/` → `commonMain/kotlin/io/kotest/property/arbs/products/`

- [ ] **Step 1: Move the three product files**

```bash
mkdir -p src/commonMain/kotlin/io/kotest/property/arbs/products
git mv src/jvmMain/kotlin/io/kotest/property/arbs/products/brands.kt   src/commonMain/kotlin/io/kotest/property/arbs/products/brands.kt
git mv src/jvmMain/kotlin/io/kotest/property/arbs/products/gpc.kt      src/commonMain/kotlin/io/kotest/property/arbs/products/gpc.kt
git mv src/jvmMain/kotlin/io/kotest/property/arbs/products/products.kt src/commonMain/kotlin/io/kotest/property/arbs/products/products.kt
```

- [ ] **Step 2: Replace `brands.kt` body**

Overwrite `src/commonMain/kotlin/io/kotest/property/arbs/products/brands.kt` with:

```kotlin
package io.kotest.property.arbs.products

import io.kotest.property.Arb
import io.kotest.property.arbitrary.map
import io.kotest.property.arbitrary.of
import io.kotest.property.arbs.generated.brandsData

data class Brand(val value: String)

fun Arb.Companion.brand() = Arb.of(brandsData).map(::Brand)
```

- [ ] **Step 3: Replace `gpc.kt` body**

Overwrite `src/commonMain/kotlin/io/kotest/property/arbs/products/gpc.kt` with:

```kotlin
package io.kotest.property.arbs.products

import io.kotest.property.Arb
import io.kotest.property.arbitrary.map
import io.kotest.property.arbitrary.of
import io.kotest.property.arbs.generated.gpcData

fun Arb.Companion.googleTaxonomy() = Arb.of(gpcData).map { GoogleTaxonomy(it) }

data class GoogleTaxonomy(val value: String)
```

- [ ] **Step 4: `products.kt` needs no body change — only its location moved**

Open `src/commonMain/kotlin/io/kotest/property/arbs/products/products.kt` and confirm there are no JVM-only references (`java.*`, `kotlin.io.File`, etc.). It should already be multiplatform-clean. The `fun main()` at the bottom is fine in commonMain — it just won't be a JVM entry point any more, but it still compiles.

> If `fun main()` causes JS compilation issues (the JS target may complain about a top-level `main` it doesn't know how to wire), wrap it as JVM-only by deleting it. There is already `run.kt` in jvmMain that does the same demo for usernames; keeping the demo only in jvmMain is consistent. Delete the `fun main()` block at the bottom of `products.kt`:
>
> ```kotlin
> // Delete:
> fun main() {
>   Arb.products().take(100).forEach(::println)
> }
> ```
>
> Also remove the now-unused `import io.kotest.property.arbitrary.take` if compilation warns.

- [ ] **Step 5: Build for both targets**

Run: `./gradlew compileKotlinJvm compileKotlinJs`
Expected: PASS.

- [ ] **Step 6: Run jvmTest**

Run: `./gradlew jvmTest`
Expected: PASS. `BrandTest` and `GoogleTaxonomyTest` continue to pass on their pinned seeds:
- `BrandTest`: seed 2313123 → `Brand("Olivia Riegel")`, seed 345 → `Brand("Ring Relief")`, seed 9143 → `Brand("Cockpit USA")`.

- [ ] **Step 7: Commit**

```bash
git add -A
git commit -m "Migrate products/brands, gpc, products arbs to commonMain

Brands and Google Taxonomy now read from generated data constants;
products.kt has no resource access and just moves location. The
products.kt demo main() is removed to keep the JS compile clean (the
demo for usernames in jvmMain run.kt is unaffected).

Co-Authored-By: Claude Opus 4.7 (1M context) <noreply@anthropic.com>"
```

---

## Task 7: Migrate `wine/wine.kt` and `movies/harrypotter.kt`

**Files:**
- Move: `src/jvmMain/kotlin/io/kotest/property/arbs/wine/wine.kt` → `src/commonMain/kotlin/io/kotest/property/arbs/wine/wine.kt`
- Move: `src/jvmMain/kotlin/io/kotest/property/arbs/movies/harrypotter.kt` → `src/commonMain/kotlin/io/kotest/property/arbs/movies/harrypotter.kt`

- [ ] **Step 1: Move the files**

```bash
mkdir -p src/commonMain/kotlin/io/kotest/property/arbs/wine
mkdir -p src/commonMain/kotlin/io/kotest/property/arbs/movies
git mv src/jvmMain/kotlin/io/kotest/property/arbs/wine/wine.kt              src/commonMain/kotlin/io/kotest/property/arbs/wine/wine.kt
git mv src/jvmMain/kotlin/io/kotest/property/arbs/movies/harrypotter.kt     src/commonMain/kotlin/io/kotest/property/arbs/movies/harrypotter.kt
```

- [ ] **Step 2: Replace `wine.kt` body**

Overwrite `src/commonMain/kotlin/io/kotest/property/arbs/wine/wine.kt` with:

```kotlin
package io.kotest.property.arbs.wine

import io.kotest.property.Arb
import io.kotest.property.arbitrary.flatMap
import io.kotest.property.arbitrary.map
import io.kotest.property.arbitrary.of
import io.kotest.property.arbs.Name
import io.kotest.property.arbs.generated.wine.wineRegionData
import io.kotest.property.arbs.generated.wine.wineVarietyData
import io.kotest.property.arbs.generated.wine.wineVineyardsData
import io.kotest.property.arbs.generated.wine.wineWineryData
import io.kotest.property.arbs.name
import kotlin.random.Random

fun Arb.Companion.vineyards() = Arb.of(wineVineyardsData).map { Vineyard(it) }
fun Arb.Companion.wineRegions() = Arb.of(wineRegionData).map { WineRegion(it) }
fun Arb.Companion.wineries() = Arb.of(wineWineryData).map { Winery(it) }
fun Arb.Companion.wineVarities() = Arb.of(wineVarietyData).map { WineVariety(it) }

fun Arb.Companion.wines() = vineyards().flatMap { vineyard ->
  wineRegions().flatMap { region ->
    wineVarities().flatMap { variety ->
      wineries().map { winery ->
        Wine(vineyard, variety, winery, region, Random.nextInt(1920, 2020))
      }
    }
  }
}

fun Arb.Companion.wineReviews() = wines().flatMap { wine ->
  name().map { name ->
    WineReview(wine, Random.nextDouble(0.1, 5.0), name)
  }
}

data class Wine(
  val vineyard: Vineyard,
  val variety: WineVariety,
  val winery: Winery,
  val region: WineRegion,
  val year: Int
)

data class WineReview(val wine: Wine, val rating: Double, val name: Name)
data class Vineyard(val value: String)
data class WineVariety(val value: String)
data class Winery(val value: String)
data class WineRegion(val value: String)
```

> The original file had a demo `fun main()` at the bottom. Drop it for the same reason as Task 6 step 4: keep JS compilation clean and consolidate demos in `jvmMain/run.kt`.

- [ ] **Step 3: Replace `harrypotter.kt` body**

Overwrite `src/commonMain/kotlin/io/kotest/property/arbs/movies/harrypotter.kt` with:

```kotlin
package io.kotest.property.arbs.movies

import io.kotest.property.Arb
import io.kotest.property.arbitrary.arbitrary
import io.kotest.property.arbs.generated.harrypotterFirstNamesData
import io.kotest.property.arbs.generated.harrypotterLastNamesData

data class Character(val firstName: String, val lastName: String)

fun Arb.Companion.harryPotterCharacter() = arbitrary {
  Character(
    harrypotterFirstNamesData.random(it.random),
    harrypotterLastNamesData.random(it.random)
  )
}
```

> Demo `main()` removed for the same JS-clean reason.

- [ ] **Step 4: Build for both targets**

Run: `./gradlew compileKotlinJvm compileKotlinJs`
Expected: PASS.

- [ ] **Step 5: Run jvmTest**

Run: `./gradlew jvmTest`
Expected: PASS — full suite green.

- [ ] **Step 6: Commit**

```bash
git add -A
git commit -m "Migrate wine and harrypotter arbs to commonMain

Both now read from generated data constants under
io.kotest.property.arbs.generated.wine and ...generated respectively.
Removed unused demo main() blocks to keep JS compilation clean.

Co-Authored-By: Claude Opus 4.7 (1M context) <noreply@anthropic.com>"
```

---

## Task 8: Migrate `tube/tube.kt` (CSV → generated `Station` list, `kotlinx-datetime` swap)

**Files:**
- Move: `src/jvmMain/kotlin/io/kotest/property/arbs/tube/tube.kt` → `src/commonMain/kotlin/io/kotest/property/arbs/tube/tube.kt`

- [ ] **Step 1: Move the file**

```bash
mkdir -p src/commonMain/kotlin/io/kotest/property/arbs/tube
git mv src/jvmMain/kotlin/io/kotest/property/arbs/tube/tube.kt src/commonMain/kotlin/io/kotest/property/arbs/tube/tube.kt
```

- [ ] **Step 2: Replace `tube.kt` body**

Overwrite `src/commonMain/kotlin/io/kotest/property/arbs/tube/tube.kt` with:

```kotlin
package io.kotest.property.arbs.tube

import io.kotest.property.Arb
import io.kotest.property.arbitrary.arbitrary
import io.kotest.property.arbitrary.flatMap
import io.kotest.property.arbitrary.map
import io.kotest.property.arbs.generated.tube.tubeStationsData
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import kotlin.random.Random

data class Station(
  val id: Long,
  val latitude: Double,
  val longitude: Double,
  val name: String,
  val zone: Double,
  val lines: Int,
  val rail: Int
)

enum class FareMethod {
  Oyster, Contactless, NetworkRail, Mobile
}

data class Journey(
  val start: Station,
  val end: Station,
  val date: LocalDateTime,
  val durationMinutes: Int,
  val farePence: Int,
  val method: FareMethod
)

fun Arb.Companion.tubeStation() = arbitrary { tubeStationsData.random(it.random) }

private val baseInstant =
  LocalDateTime(2020, 12, 31, 23, 59, 59, 0).toInstant(TimeZone.UTC)

fun Arb.Companion.tubeJourney() = Arb.tubeStation().flatMap { stationA ->
  Arb.tubeStation().map { stationB ->

    val instant = baseInstant
      .minus(Random.nextLong(365 * 20), DateTimeUnit.DAY, TimeZone.UTC)
      .minus(Random.nextLong(60 * 60 * 24), DateTimeUnit.SECOND)
    val date = instant.toLocalDateTime(TimeZone.UTC)

    Journey(
      stationA,
      stationB,
      date,
      Random.nextInt(1, 59),
      Random.nextInt(0, 65) * 10,
      FareMethod.values().random()
    )
  }
}
```

> Notes on the date arithmetic:
> - `kotlinx.datetime.LocalDateTime`'s constructor takes `(year, month, dayOfMonth, hour, minute, second, nanosecond)`. Passing `0` for nanosecond matches the previous `LocalDateTime.of(2020, 12, 31, 23, 59, 59)` which had nanos = 0.
> - `Instant.minus(value, DateTimeUnit.DateBased, TimeZone)` requires a `TimeZone` (we use `UTC`).
> - `Instant.minus(value, DateTimeUnit.TimeBased)` does not require a `TimeZone`.
> - The conversion is round-tripped through `Instant` because `LocalDateTime` itself has no arithmetic operations in `kotlinx-datetime` — date math always goes via `Instant`.
>
> The randomness/timing is statistically equivalent to the previous `java.time` version. There is no test that pins a specific date, so the seed-determined output is allowed to differ.

- [ ] **Step 3: Build for both targets**

Run: `./gradlew compileKotlinJvm compileKotlinJs`
Expected: PASS.

- [ ] **Step 4: Run jvmTest**

Run: `./gradlew jvmTest`
Expected: PASS — full suite green.

- [ ] **Step 5: Commit**

```bash
git add -A
git commit -m "Migrate tube arb to commonMain with kotlinx-datetime

Stations come from the generated tubeStationsData list (no runtime
CSV parsing). Date math switches from java.time.LocalDateTime to
kotlinx.datetime.LocalDateTime with Instant-based arithmetic in UTC.
No test pins a specific Journey date, so the random output drift is
acceptable.

Co-Authored-By: Claude Opus 4.7 (1M context) <noreply@anthropic.com>"
```

---

## Task 9: Delete `loader.kt`; remove `univocity-parsers` from runtime deps

**Files:**
- Delete: `src/jvmMain/kotlin/io/kotest/property/arbs/loader.kt`
- Modify: `build.gradle.kts`
- Modify: `gradle/libs.versions.toml`

- [ ] **Step 1: Confirm `loader.kt` has no remaining callers**

Run:
```bash
grep -RIn 'loadResource\|loadResourceAsLines' src
```
Expected: no matches.

- [ ] **Step 2: Delete `loader.kt`**

```bash
git rm src/jvmMain/kotlin/io/kotest/property/arbs/loader.kt
```

- [ ] **Step 3: Verify `jvmMain` is now (almost) empty**

Run: `find src/jvmMain -type f`
Expected:
```
src/jvmMain/kotlin/io/kotest/property/arbs/run.kt
```

- [ ] **Step 4: Remove `univocity-parsers` from `commonMain` runtime deps**

Edit `build.gradle.kts`. Find:

```kotlin
val commonMain by getting {
  dependencies {
    api(libs.kotest.property)
    api(libs.kotest.property.datetime)
    implementation(libs.univocity.parsers)
    implementation(libs.kotlinx.datetime)
  }
}
```

Replace with:

```kotlin
val commonMain by getting {
  dependencies {
    api(libs.kotest.property)
    api(libs.kotest.property.datetime)
    implementation(libs.kotlinx.datetime)
  }
}
```

- [ ] **Step 5: Remove `univocity-parsers` from version catalog**

Edit `gradle/libs.versions.toml`. Delete the line:

```toml
univocity-parsers = { module = "com.univocity:univocity-parsers", version = "2.9.1" }
```

- [ ] **Step 6: Build and run tests**

Run: `./gradlew clean build`
Expected: PASS — both `compileKotlinJvm`, `compileKotlinJs`, and `jvmTest` succeed.

> If `compileKotlinJvm` complains that `univocity` types are unresolved, search for residual imports: `grep -RIn 'univocity' src`. There should be no matches in `src/`. The only mention should be `buildSrc/build.gradle.kts`.

- [ ] **Step 7: Commit**

```bash
git add -A
git commit -m "Drop loader.kt and remove univocity-parsers runtime dep

loader.kt is no longer referenced anywhere — runtime resource loading
is gone. univocity-parsers is now a buildSrc-only dependency, used
purely to parse tube/stations.csv at build time. Removed it from
commonMain dependencies and from libs.versions.toml.

Co-Authored-By: Claude Opus 4.7 (1M context) <noreply@anthropic.com>"
```

---

## Task 10: Add JS smoke test proving generated data is reachable on JS

**Files:**
- Modify: `build.gradle.kts` — add `jsTest` source set with `kotlin.test` dep.
- Create: `src/jsTest/kotlin/io/kotest/property/arbs/GeneratedDataSmokeTest.kt`

- [ ] **Step 1: Add `jsTest` source set to `build.gradle.kts`**

In `build.gradle.kts`, inside the `kotlin { sourceSets { ... } }` block, after `val jvmTest by getting { ... }`, add:

```kotlin
val jsTest by getting {
  dependencies {
    implementation(kotlin("test-js"))
  }
}
```

- [ ] **Step 2: Create the smoke test file**

Create `src/jsTest/kotlin/io/kotest/property/arbs/GeneratedDataSmokeTest.kt`:

```kotlin
package io.kotest.property.arbs

import io.kotest.property.Arb
import io.kotest.property.arbitrary.take
import io.kotest.property.arbs.tube.tubeStation
import kotlin.test.Test
import kotlin.test.assertTrue

/**
 * Smoke test on the JS target. Existence of these tests passing proves the
 * data generated at build time from src/commonMain/resources is reachable
 * from JS — i.e. the migration of arbs from jvmMain to commonMain succeeded.
 */
class GeneratedDataSmokeTest {

  @Test
  fun firstNamesAreReachable() {
    val sample = Arb.firstName().take(5).toList()
    assertTrue(sample.size == 5, "expected 5 samples, got ${'$'}{sample.size}")
    assertTrue(sample.all { it.name.isNotBlank() }, "blank first name in $sample")
  }

  @Test
  fun colorsAreReachable() {
    val sample = Arb.color().take(5).toList()
    assertTrue(sample.size == 5)
    assertTrue(sample.all { it.value.isNotBlank() })
  }

  @Test
  fun tubeStationsAreReachable() {
    val sample = Arb.tubeStation().take(5).toList()
    assertTrue(sample.size == 5)
    assertTrue(sample.all { it.name.isNotBlank() })
    assertTrue(sample.all { it.id > 0 })
  }
}
```

- [ ] **Step 3: Run the JS test**

Run: `./gradlew jsTest`
Expected: PASS — all three tests green.

> If `./gradlew jsTest` is not the right task name, run `./gradlew tasks --all | grep -i jstest` to find the actual task. Likely candidates: `jsBrowserTest`, `jsNodeTest`, `jsTest`. Try those in order.

- [ ] **Step 4: Run full build**

Run: `./gradlew build`
Expected: PASS — `compileKotlinJvm`, `compileKotlinJs`, `jvmTest`, and the JS test task all green.

- [ ] **Step 5: Commit**

```bash
git add -A
git commit -m "Add JS smoke test proving migrated arbs work on JS

Three tests against firstName, color, and tubeStation arbs prove the
build-time-generated data constants are reachable from the JS target.
Uses kotlin.test rather than Kotest to keep the multiplatform test
wiring minimal.

Co-Authored-By: Claude Opus 4.7 (1M context) <noreply@anthropic.com>"
```

---

## Task 11: Final verification

- [ ] **Step 1: Clean build from scratch**

Run: `./gradlew clean build`
Expected: PASS.

- [ ] **Step 2: Verify directory layout**

Run:
```bash
find src/jvmMain -type f
find src/commonMain -type f -name '*.kt' | sort
find src/commonMain/resources -type f | sort
```

Expected:
- `src/jvmMain` contains exactly `src/jvmMain/kotlin/io/kotest/property/arbs/run.kt`.
- `src/commonMain/kotlin` contains: the previously-existing common arbs (cars, Ticker, usernames, exchanges, logins, payments/transactions, places/addresses, geo/*, fooddrink/icecreams, games/{monopoly,chess,cluedo}, travel/*) plus the migrated files (names, colors, domains, products/{brands,gpc,products}, wine/wine, movies/harrypotter, tube/tube).
- `src/commonMain/resources` contains all 14 resource files.

- [ ] **Step 3: Spot-check a generated file**

Run:
```bash
head -10 build/generated/sources/arbs-data/commonMain/kotlin/io/kotest/property/arbs/generated/tube/TubeStationsData.kt
```
Expected: a `package io.kotest.property.arbs.generated.tube` header followed by `internal val tubeStationsData: List<Station> = listOf(` and `Station(...)` entries.

- [ ] **Step 4: Confirm no stale references**

Run:
```bash
grep -RIn 'loadResource\|univocity\|java\.time' src
```
Expected: no matches. (`buildSrc` may still mention `univocity` and `java.io`, which is fine — that's not under `src/`.)

- [ ] **Step 5: Run the full test matrix one more time**

Run: `./gradlew jvmTest jsTest`
Expected: PASS for both. Summarise the outcome (counts of passed tests per target) in the final commit message of this task or in the PR body.

- [ ] **Step 6: Optional — confirm publishing still works locally**

Run: `./gradlew publishToMavenLocal`
Expected: PASS. Inspect `~/.m2/repository/io/kotest/extensions/<artifact>` and confirm a JVM jar plus a Kotlin metadata jar were produced. (Failure mode: if removing `univocity-parsers` broke the published POM, this is where it shows up.)

- [ ] **Step 7: Final commit (if any cleanup needed)**

If steps 2–6 surfaced anything (e.g., a stray import, a missed file), fix it and:

```bash
git add -A
git commit -m "Cleanup after final verification

Co-Authored-By: Claude Opus 4.7 (1M context) <noreply@anthropic.com>"
```

Otherwise, no commit; this task is verification-only.

---

## Self-Review Notes

- **Spec coverage:** every section of the spec maps to a task. Generator → T1, T2; wiring → T3; resource move → T4; arb migrations → T5–T8; cleanup of `loader.kt` and dependencies → T9; commonTest fallback (jsTest) → T10; verification → T11.
- **Naming consistency:** `firstNamesData`, `lastNamesData`, `colorsData`, `countryTldsData`, `brandsData`, `gpcData`, `harrypotterFirstNamesData`, `harrypotterLastNamesData`, `wineCountryData`, `wineRegionData`, `wineVarietyData`, `wineVineyardsData`, `wineWineryData`, `tubeStationsData` — used identically across spec, generator implementation in T1/T2, and consuming arb files in T5–T8.
- **Type/method signatures:** `Station(id: Long, latitude: Double, longitude: Double, name: String, zone: Double, lines: Int, rail: Int)` definition (T8) matches the constructor calls the generator emits (T2). `LocalDateTime` constructor `(year, month, dayOfMonth, hour, minute, second, nanosecond)` is the canonical `kotlinx.datetime` shape used in T8.
- **Out-of-scope items honored:** no Kotlin/Gradle bump, no public API change, no new platform targets, no new arbs.
