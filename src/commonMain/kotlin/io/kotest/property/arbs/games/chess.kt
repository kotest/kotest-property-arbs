package io.kotest.property.arbs.games

import io.kotest.property.Arb
import io.kotest.property.arbitrary.arbitrary
import io.kotest.property.arbitrary.bind
import io.kotest.property.arbitrary.of
import io.kotest.property.arbitrary.orNull

data class ChessPiece(val name: String, val points: Int) {
  companion object {
    val all = listOf(
      ChessPiece("Pawn", 1),
      ChessPiece("Bishop", 5),
      ChessPiece("Knight", 5),
      ChessPiece("Rook", 5),
      ChessPiece("Queen", 9),
      ChessPiece("King", 4),
    )
  }
}

fun Arb.Companion.chessSquare(): Arb<String> = arbitrary {
  ('A' + it.random.nextInt(0, 8)).toString() + it.random.nextInt(1, 9)
}

fun Arb.Companion.chessPiece() = Arb.of(ChessPiece.all)

data class ChessMove(val from: String, val to: String, val capture: ChessPiece?)

fun Arb.Companion.chessMove(): Arb<ChessMove> = Arb.bind(
  Arb.chessSquare(),
  Arb.chessSquare(),
  Arb.chessPiece().orNull(0.9)
) { from, to, capture ->
  ChessMove(from, to, capture)
}
