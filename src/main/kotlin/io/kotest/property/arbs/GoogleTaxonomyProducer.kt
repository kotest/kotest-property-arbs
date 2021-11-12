package io.kotest.property.arbs

object GoogleTaxonomyProducer : Producer<GoogleTaxonomy> {
   private val brands = loadResourceAsLines("/gpc.txt")
   override fun produce(): GoogleTaxonomy {
      return GoogleTaxonomy(brands.random())
   }
}

data class GoogleTaxonomy(val value: String)