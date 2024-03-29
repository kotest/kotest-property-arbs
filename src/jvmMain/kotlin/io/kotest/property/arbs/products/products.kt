package io.kotest.property.arbs.products

import io.kotest.property.Arb
import io.kotest.property.arbitrary.flatMap
import io.kotest.property.arbitrary.map
import io.kotest.property.arbitrary.take
import io.kotest.property.arbs.Color
import io.kotest.property.arbs.color
import kotlin.random.Random

data class Product(
  val sku: Sku,
  val brand: Brand,
  val gtin: Gtin,
  val price: Int, // cents
  val quantity: Int,
  val material: String,
  val color: Color?,
  val size: String,
  val name: String,
  val taxonomy: GoogleTaxonomy
)

data class Sku(val value: String)
data class Gtin(val value: String)

fun Arb.Companion.products() = color().flatMap { color ->
  brand().flatMap { brand ->
    googleTaxonomy().map { taxonomy ->

      val sku = List(3) { ('A'..'Z').random() }.joinToString("") + List(8) { Random.nextInt(0, 10) }.joinToString("")
      val gtin = List(12) { Random.nextInt(0, 10) }.joinToString("")

      Product(
        sku = Sku(sku),
        brand = brand,
        gtin = Gtin(gtin),
        price = Random.nextInt(1000, 25000),
        quantity = Random.nextInt(0, 999),
        material = listOf("wool", "leather", "silk", "jersey", "cotton", "nylon").random(),
        color = color,
        size = listOf("XS", "S", "M", "L", "XL", "XXL", "XXXL").random(),
        name = listOf(
          "onesie",
          "dress shirt",
          "neck tie",
          "varsity top",
          "jumper",
          "summer top",
          "zipped jacket",
          "sweater",
          "hoodie",
          "sneakers",
          "blouse",
          "tuxedo",
          "sports tee",
          "running top",
          "long sleeve shirt",
          "short sleeve shirt",
          "winter jacket"
        ).random(),
        taxonomy = taxonomy
      )
    }
  }
}

fun main() {
  Arb.products().take(100).forEach(::println)
}
