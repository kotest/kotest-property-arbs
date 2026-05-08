package io.kotest.property.arbs.games

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll

class BoardGameTest : FunSpec() {
  init {
    test("boardGame emits non-blank fields") {
      checkAll(20, Arb.boardGame()) { game ->
        game.name.isNotBlank() shouldBe true
        game.publisher.isNotBlank() shouldBe true
        game.type.isNotBlank() shouldBe true
        game.recommendedPlayers.isNotBlank() shouldBe true
      }
    }
  }
}
