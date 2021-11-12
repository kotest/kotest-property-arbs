package io.kotest.property.arbs.games

import io.kotest.property.Arb
import io.kotest.property.arbitrary.arbitrary
import io.kotest.property.arbitrary.flatMap
import io.kotest.property.arbitrary.of

data class CluedoSuspect(val name: String) {
  companion object {
    val all = listOf(
      CluedoSuspect("Colonel Mustard"),
      CluedoSuspect("Miss Scarlett"),
      CluedoSuspect("Mrs. Peacock"),
      CluedoSuspect("Mrs. White"),
      CluedoSuspect("Reverend Green"),
      CluedoSuspect("Professor Plum"),
    )
  }
}

data class CluedoWeapon(val name: String) {
  companion object {
    val all = listOf(
      CluedoWeapon("Revolver"),
      CluedoWeapon("Dagger"),
      CluedoWeapon("Spanner"),
      CluedoWeapon("Rope"),
      CluedoWeapon("Lead Piping"),
      CluedoWeapon("Candlestick"),
    )
  }
}

data class CluedoLocation(val name: String) {
  companion object {
    val all = listOf(
      CluedoLocation("Ballroom"),
      CluedoLocation("Billiard Room"),
      CluedoLocation("Conservatory"),
      CluedoLocation("Dining Room"),
      CluedoLocation("Hall"),
      CluedoLocation("Library"),
      CluedoLocation("Lounge"),
      CluedoLocation("Kitchen"),
      CluedoLocation("Study"),
    )
  }
}

data class CluedoAccusation(
  val suspect: CluedoSuspect,
  val weapon: CluedoWeapon,
  val location: CluedoLocation,
  val correctGuess: Boolean,
)

fun Arb.Companion.cluedoSuspects() = Arb.of(CluedoSuspect.all)
fun Arb.Companion.cluedoWeapons() = Arb.of(CluedoWeapon.all)
fun Arb.Companion.cluedoLocations() = Arb.of(CluedoLocation.all)
fun Arb.Companion.cluedoAccusations() = cluedoSuspects().flatMap { suspect ->
  cluedoWeapons().flatMap { weapon ->
    cluedoLocations().flatMap { location ->
      arbitrary {
        CluedoAccusation(suspect, weapon, location, it.random.nextDouble() < 0.05)
      }
    }
  }
}
