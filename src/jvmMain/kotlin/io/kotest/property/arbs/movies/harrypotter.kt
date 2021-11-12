package io.kotest.property.arbs.movies

import io.kotest.property.Arb
import io.kotest.property.arbitrary.arbitrary
import io.kotest.property.arbitrary.take
import io.kotest.property.arbs.loadResourceAsLines

private val first = loadResourceAsLines("/harrypotter_first_names.csv")
private val last = loadResourceAsLines("/harrypotter_last_names.csv")

data class Character(val firstName: String, val lastName: String)

fun Arb.Companion.harryPotterCharacter() = arbitrary {
  Character(first.random(it.random), last.random(it.random))
}

fun main() {
  Arb.harryPotterCharacter().take(100).forEach(::println)
}
