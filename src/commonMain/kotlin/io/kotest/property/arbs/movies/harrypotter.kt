package io.kotest.property.arbs.movies

import io.kotest.property.Arb
import io.kotest.property.arbitrary.arbitrary
import io.kotest.property.arbs.generated.harrypotterFirstNamesData
import io.kotest.property.arbs.generated.harrypotterLastNamesData

data class Character(val firstName: String, val lastName: String)

fun Arb.Companion.harryPotterCharacter() = arbitrary {
  Character(
    harrypotterFirstNamesData.random(it.random),
    harrypotterLastNamesData.random(it.random)
  )
}
