package io.kotest.property.arbs.geo

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll

class GreekIslandTest : FunSpec() {
  init {
    test("greekIsland emits non-blank fields and at least one sea") {
      checkAll(20, Arb.greekIsland()) { island ->
        island.name.isNotBlank() shouldBe true
        island.capital.isNotBlank() shouldBe true
        island.seas.isNotEmpty() shouldBe true
        island.seas.all { it.isNotBlank() } shouldBe true
      }
    }
  }
}
