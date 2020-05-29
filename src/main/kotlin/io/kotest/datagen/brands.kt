package io.kotest.datagen

object BrandProducer : Producer<Brand> {
   private val brands = javaClass.getResourceAsStream("/brands.txt").bufferedReader().readLines()
   override fun produce(): Brand {
      return Brand(brands.random())
   }
}

data class Brand(val value: String)