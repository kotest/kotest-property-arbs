package io.kotest.property.arbs.products

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.PropTestConfig
import io.kotest.property.checkAll

class GoogleTaxonomyTest : FunSpec() {
  init {
    test("happy path") {
      checkAll(1, PropTestConfig(2313123), Arb.googleTaxonomy()) { tax ->
        tax shouldBe GoogleTaxonomy("Food, Beverages & Tobacco > Food Items > Fruits & Vegetables > Fresh & Frozen Vegetables > Malangas")
      }
    }
  }
}
