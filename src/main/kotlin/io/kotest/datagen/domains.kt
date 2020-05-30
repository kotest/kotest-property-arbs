package io.kotest.datagen

import kotlin.random.Random

/**
 * Generates domains where the domain name is a random string, and the tld is a real country level tld.
 * Will randomly include www, cdn and www2 prefixes.
 */
object DomainProducer : Producer<Domain> {
   private val prefixes = listOf("www", "www2", "cdn", null)
   private val tlds = javaClass.getResourceAsStream("/country_tlds.txt").bufferedReader().readLines()
   private val az = 'a'..'z'
   override fun produce(): Domain {
      val prefix = prefixes.random()
      val middle = List(Random.nextInt(4, 14)) { az.random() }.joinToString()
      val domain = listOfNotNull(prefix, middle, tlds.random()).joinToString(".")
      return Domain(domain)
   }
}

data class Domain(val value: String)