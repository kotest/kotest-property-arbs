package io.kotest.property.arbs.places

import io.kotest.property.Arb
import io.kotest.property.arbitrary.bind
import io.kotest.property.arbitrary.element
import io.kotest.property.arbitrary.int
import io.kotest.property.arbs.colours

private val endings = listOf(
  "Avenue",
  "Road",
  "Street",
  "Place",
  "Terrace",
  "Drive",
  "Court",
  "Way",
  "Grove",
  "Walk",
  "Crescent",
  "Mews",
  "Close",
  "Rise",
  "Row",
  "Wynd",
  "Meadow",
  "Park",
  "End",
  "Cross",
)

private val trees = listOf(
  "Chestnut",
  "Locust",
  "Cherry",
  "Oak",
  "Elm",
  "Birch",
  "River Birch",
  "Pinoak",
  "Crabapple",
  "Vine",
  "Spruce",
  "Horse Chestnut",
  "Pine",
  "Sycamore",
  "Walnut",
  "Fir",
)

private fun Arb.Companion.trees(): Arb<String> = Arb.element(trees)
private fun Arb.Companion.endings(): Arb<String> = Arb.element(endings)
private fun Arb.Companion.cols(): Arb<String> = Arb.element(colours)

fun Arb.Companion.addresses() = Arb.bind(
  Arb.cols(),
  Arb.trees(),
  Arb.endings(),
  Arb.int(1..9999)
) { color, name, ending, number ->
  "$number $color $name $ending".uppercase()
}
