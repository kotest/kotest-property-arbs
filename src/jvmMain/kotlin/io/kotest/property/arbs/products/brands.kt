package io.kotest.property.arbs.products

import io.kotest.property.Arb
import io.kotest.property.arbitrary.map
import io.kotest.property.arbitrary.of
import io.kotest.property.arbs.loadResourceAsLines

private val brands = lazy { loadResourceAsLines("/brands.txt") }

data class Brand(val value: String)

fun Arb.Companion.brand() = Arb.of(brands.value).map(::Brand)

