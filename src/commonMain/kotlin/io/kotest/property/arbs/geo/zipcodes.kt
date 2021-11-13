package io.kotest.property.arbs.geo

import io.kotest.property.Arb
import io.kotest.property.arbitrary.arbitrary

data class Zipcode(val value: String)

fun Arb.Companion.zipcodes() = arbitrary { it.random.nextInt(1000, 99999).toString().padStart(5, '0') }
