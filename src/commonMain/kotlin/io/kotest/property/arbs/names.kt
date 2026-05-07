package io.kotest.property.arbs

import io.kotest.property.Arb
import io.kotest.property.arbitrary.arbitrary
import io.kotest.property.arbitrary.flatMap
import io.kotest.property.arbitrary.map
import io.kotest.property.arbs.generated.firstNamesData
import io.kotest.property.arbs.generated.lastNamesData

data class FirstName(val name: String)
data class LastName(val name: String)
data class Name(val first: FirstName, val last: LastName)

fun Arb.Companion.firstName() = arbitrary { FirstName(firstNamesData.random(it.random)) }
fun Arb.Companion.lastName() = arbitrary { LastName(lastNamesData.random(it.random)) }
fun Arb.Companion.name() = firstName().flatMap { first -> lastName().map { last -> Name(first, last) } }
