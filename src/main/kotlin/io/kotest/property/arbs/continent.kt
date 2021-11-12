package io.kotest.property.arbs

enum class Continent {
  Africa,
  Antartica,
  Asia,
  Europe,
  NorthAmerica,
  SouthAmerica,
  Oceania
}

object ContinentProducer : Producer<Continent> {
  override fun produce(): Continent = Continent.values().random()
}