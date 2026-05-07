package io.kotest.property.arbs.build

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
}
