package io.kotest.property.arbs.politics

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll

class UkPrimeMinisterTest : FunSpec() {
  init {
    test("ukPrimeMinister emits non-blank fields") {
      checkAll(20, Arb.ukPrimeMinister()) { pm ->
        pm.name.isNotBlank() shouldBe true
        pm.party.isNotBlank() shouldBe true
        pm.yearsInOffice.isNotBlank() shouldBe true
      }
    }
  }
}
