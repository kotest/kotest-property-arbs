package io.kotest.datagen

object GoogleTaxonomyProducer : Producer<GoogleTaxonomy> {
   private val brands = javaClass.getResourceAsStream("/gpc.txt").bufferedReader().readLines()
   override fun produce(): GoogleTaxonomy {
      return GoogleTaxonomy(brands.random())
   }
}

data class GoogleTaxonomy(val value: String)