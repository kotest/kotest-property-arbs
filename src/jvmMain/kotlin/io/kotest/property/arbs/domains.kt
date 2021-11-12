package io.kotest.property.arbs

import io.kotest.property.Arb
import io.kotest.property.arbitrary.arbitrary

/**
 * Generates domains where the domain name is a random string with the given length,
 * and the tld is a real country level tld.
 *
 * Will randomly include www, cdn and www2 prefixes.
 */
fun Arb.Companion.domain(nameLength: IntRange = 3..20): Arb<Domain> = arbitrary { rs ->
  val prefix = prefixes.random(rs.random)
  val name = List(rs.random.nextInt(nameLength.first, nameLength.last)) { ('a'..'z').random(rs.random) }.joinToString("")
  val domain = listOfNotNull(prefix, name, tlds.random(rs.random)).joinToString(".")
  Domain(domain)
}

private val prefixes = listOf("www", "www2", "cdn", null)
private val tlds = loadResourceAsLines("/country_tlds.txt")

data class Domain(val value: String)
