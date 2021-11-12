package io.kotest.property.arbs

import io.kotest.property.Arb
import io.kotest.property.arbitrary.arbitrary
import io.kotest.property.arbitrary.flatMap
import io.kotest.property.arbitrary.map

data class FirstName(val name: String)
data class LastName(val name: String)
data class Name(val first: FirstName, val last: LastName)

private val firstNames = lazy { loadResourceAsLines("/first_names.txt") }
private val lastNames = lazy { loadResourceAsLines("/last_names.txt") }

fun Arb.Companion.firstName() = arbitrary { FirstName(firstNames.value.random(it.random)) }
fun Arb.Companion.lastName() = arbitrary { LastName(lastNames.value.random(it.random)) }
fun Arb.Companion.name() = firstName().flatMap { first -> lastName().map { last -> Name(first, last) } }
