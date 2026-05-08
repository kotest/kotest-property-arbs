package io.kotest.property.arbs

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll

class StreamingServiceTest : FunSpec() {
  init {
    test("streamingService emits a non-blank name") {
      checkAll(20, Arb.streamingService()) { it.isNotBlank() shouldBe true }
    }
  }
}
