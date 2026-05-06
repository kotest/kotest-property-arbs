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
    assertTrue(sample.size == 5, "expected 5 samples, got ${sample.size}")
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
