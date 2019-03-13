package io.kotlintest.datagen

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