package io.kotest.property.arbs.geo

import io.kotest.property.Arb
import io.kotest.property.arbitrary.of

enum class Continent {
  Africa,
  Antartica,
  Asia,
  Europe,
  NorthAmerica,
  SouthAmerica,
  Oceania
}

fun Arb.Companion.continent() = Arb.of(Continent.values())
