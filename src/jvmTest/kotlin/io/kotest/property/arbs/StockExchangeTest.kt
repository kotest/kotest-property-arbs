package io.kotest.property.arbs

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.PropTestConfig
import io.kotest.property.geo.Country
import io.kotest.property.checkAll

class StockExchangeTest : FunSpec() {
  init {
    test("happy path") {
      checkAll(1, PropTestConfig(23523), Arb.stockExchanges()) {
        it shouldBe
          StockExchange(name = "Palestine Securities Exchange", symbol = "PEX", country = Country.PalestinianTerritory)
      }
      checkAll(1, PropTestConfig(8234), Arb.stockExchanges()) {
        it shouldBe
          StockExchange(name = "BX Swiss", symbol = "BX", country = Country.Switzerland)
      }
    }
  }
}
