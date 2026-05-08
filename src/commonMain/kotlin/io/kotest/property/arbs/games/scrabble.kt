package io.kotest.property.arbs.games

import io.kotest.property.Arb
import io.kotest.property.arbitrary.arbitrary

data class ScrabbleTile(val letter: Char, val points: Int) {
  companion object {
    val Blank = ScrabbleTile('_', 0)

    private val distribution: List<Triple<Char, Int, Int>> = listOf(
      Triple('A', 1, 9), Triple('B', 3, 2), Triple('C', 3, 2), Triple('D', 2, 4),
      Triple('E', 1, 12), Triple('F', 4, 2), Triple('G', 2, 3), Triple('H', 4, 2),
      Triple('I', 1, 9), Triple('J', 8, 1), Triple('K', 5, 1), Triple('L', 1, 4),
      Triple('M', 3, 2), Triple('N', 1, 6), Triple('O', 1, 8), Triple('P', 3, 2),
      Triple('Q', 10, 1), Triple('R', 1, 6), Triple('S', 1, 4), Triple('T', 1, 6),
      Triple('U', 1, 4), Triple('V', 4, 2), Triple('W', 4, 2), Triple('X', 8, 1),
      Triple('Y', 4, 2), Triple('Z', 10, 1),
    )

    val bag: List<ScrabbleTile> = buildList {
      for ((letter, points, count) in distribution) {
        repeat(count) { add(ScrabbleTile(letter, points)) }
      }
      repeat(2) { add(Blank) }
    }
  }
}

data class ScrabbleHand(val tiles: List<ScrabbleTile>) {
  val score: Int = tiles.sumOf { it.points }
}

fun Arb.Companion.scrabbleHand() = arbitrary {
  ScrabbleHand(ScrabbleTile.bag.shuffled(it.random).take(7))
}
