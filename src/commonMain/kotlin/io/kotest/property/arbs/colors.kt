package io.kotest.property.arbs

import io.kotest.property.Arb
import io.kotest.property.arbitrary.map
import io.kotest.property.arbitrary.of
import io.kotest.property.arbs.generated.colorsData

data class Color(val value: String)

fun Arb.Companion.color() = Arb.of(colorsData).map { Color(it) }
