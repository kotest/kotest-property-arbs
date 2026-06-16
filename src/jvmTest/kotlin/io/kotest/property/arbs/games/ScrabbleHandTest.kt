package io.kotest.property.arbs.games

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll

class ScrabbleHandTest : FunSpec() {
  init {
    test("scrabbleHand has 7 tiles drawn from the standard bag") {
      checkAll(50, Arb.scrabbleHand()) { hand ->
        hand.tiles.size shouldBe 7
        hand.tiles.all { it in ScrabbleTile.bag } shouldBe true
        hand.score shouldBe hand.tiles.sumOf { it.points }
      }
    }

    test("the standard bag contains 100 tiles") {
      ScrabbleTile.bag.size shouldBe 100
    }
  }
}
