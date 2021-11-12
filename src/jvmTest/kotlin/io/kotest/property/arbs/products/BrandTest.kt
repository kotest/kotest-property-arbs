package io.kotest.property.arbs.products

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.PropTestConfig
import io.kotest.property.checkAll

class BrandTest : FunSpec() {
  init {
    test("happy path") {
      checkAll(1, PropTestConfig(2313123), Arb.brand()) { it shouldBe Brand("Hamco") }
      checkAll(1, PropTestConfig(345), Arb.brand()) { it shouldBe Brand("Hidden Hitch") }
      checkAll(1, PropTestConfig(9143), Arb.brand()) { it shouldBe Brand("BrakeBest Select Trailer Parts") }
    }
  }
}
