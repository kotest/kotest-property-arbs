package io.kotest.property.arbs

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.PropTestConfig
import io.kotest.property.checkAll

class DomainTest : FunSpec() {
  init {
    test("arb.domain should respect name length") {
      checkAll(1, PropTestConfig(23523), Arb.domain(5..10)) { it shouldBe Domain("cdn.bzfsaepnr.tj") }
      checkAll(1, PropTestConfig(8234), Arb.domain(8..14)) { it shouldBe Domain("www.dwjjohdm.ad") }
    }
  }
}
