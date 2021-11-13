package io.kotest.property.arbs.games

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldContain
import io.kotest.property.Arb
import io.kotest.property.arbitrary.take
import io.kotest.property.checkAll

class ChessTest : FunSpec() {

  private val squares = ('A'..'H').toList().flatMap { file ->
    (1..8).toList().map { rank ->
      "$file$rank"
    }
  }

  init {
    test("Arb.chessSquare should be in valid range") {
      checkAll(Arb.chessSquare()) { squares.shouldContain(it) }
    }

    test("Arb.chessSquare should cover all rank and files") {
      (squares - Arb.chessSquare().take(10000)).shouldBeEmpty()
    }
  }
}
