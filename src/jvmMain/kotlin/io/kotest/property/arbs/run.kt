package io.kotest.property.arbs

import io.kotest.property.Arb
import io.kotest.property.arbitrary.take
import io.kotest.property.arbs.games.chessMove
import io.kotest.property.arbs.travel.airJourney

fun main() {
  Arb.airJourney().take(100).forEach(::println)
  Arb.chessMove().take(100).forEach(::println)
}
