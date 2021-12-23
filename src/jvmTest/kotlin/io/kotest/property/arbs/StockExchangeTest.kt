package io.kotest.property.arbs

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.PropTestConfig
import io.kotest.property.arbs.geo.Country
import io.kotest.property.checkAll

class StockExchangeTest : FunSpec() {
  init {
    test("happy path") {
      checkAll(1, PropTestConfig(23523), Arb.stockExchanges()) {
        it shouldBe
          StockExchange(name = "Kazakhstan Stock Exchange", symbol = "KASE", country = Country.Kazakhstan)
      }
      checkAll(1, PropTestConfig(8234), Arb.stockExchanges()) {
        it shouldBe
          StockExchange(name = "Saudi Stock Exchange", symbol = "Tadawul", country = Country.SaudiArabia)
      }
    }
  }
}
