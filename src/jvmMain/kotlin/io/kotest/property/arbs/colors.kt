package io.kotest.property.arbs

import io.kotest.property.Arb
import io.kotest.property.arbitrary.map
import io.kotest.property.arbitrary.of

private val colors = loadResourceAsLines("/colors.txt")

data class Color(val value: String)

fun Arb.Companion.color() = Arb.of(colors).map { Color(it) }
