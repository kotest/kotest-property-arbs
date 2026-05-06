package io.kotest.property.arbs.products

import io.kotest.property.Arb
import io.kotest.property.arbitrary.map
import io.kotest.property.arbitrary.of
import io.kotest.property.arbs.generated.gpcData

fun Arb.Companion.googleTaxonomy() = Arb.of(gpcData).map { GoogleTaxonomy(it) }

data class GoogleTaxonomy(val value: String)
