package io.kotest.property.arbs.products

import io.kotest.property.Arb
import io.kotest.property.arbitrary.map
import io.kotest.property.arbitrary.of
import io.kotest.property.arbs.loadResourceAsLines

fun Arb.Companion.googleTaxonomy() = Arb.of(taxonomies.value).map { GoogleTaxonomy(it) }

private val taxonomies = lazy { loadResourceAsLines("/gpc.txt") }

data class GoogleTaxonomy(val value: String)
