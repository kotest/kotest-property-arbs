package io.kotest.property.arbs

import io.kotest.property.Arb
import io.kotest.property.arbitrary.arbitrary

data class Username(val value: String)

private val colours = listOf(
  "red",
  "orange",
  "yellow",
  "green",
  "cyan",
  "blue",
  "magenta",
  "purple",
  "white",
  "black",
  "grey",
  "silver",
  "pink",
  "maroon",
  "brown",
  "beige",
  "tan",
  "peach",
  "lime",
  "olive",
  "turquoise",
  "teal",
  "navy blue",
  "indigo",
  "violet",
)

private val sizes = listOf(
  "large",
  "small",
  "medium",
  "giant",
  "huge",
  "tiny",
  "minor",
  "major",
  "big",
  "little",
  "mini",
  "maxi",
  "pocket",
  "micro",
  "slight",
  "great",
  "massive",
  "colossal",
  "broad",
)

private val animals = listOf(
  "hog",
  "pig",
  "cow",
  "dog",
  "cat",
  "mouse",
  "rat",
  "rabbit",
  "sheep",
  "horse",
  "donkey",
  "ass",
  "monkey",
  "chimp",
  "fox",
  "hare",
  "goose",
  "duck",
  "frog",
  "toad",
  "beetle",
  "hound",
  "elephant",
  "lion",
  "tiger",
  "cougar",
  "panther",
  "leopard"
)

fun Arb.Companion.usernames(): Arb<Username> = arbitrary { rs ->
  val username = listOf(
    sizes.random(rs.random),
    colours.random(rs.random),
    animals.random(rs.random),
    rs.random.nextInt(1000, 9999)
  ).joinToString("_")
  Username(username)
}
