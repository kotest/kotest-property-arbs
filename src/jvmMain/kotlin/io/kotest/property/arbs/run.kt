package io.kotest.property.arbs

import io.kotest.property.Arb
import io.kotest.property.arbitrary.take
import io.kotest.property.arbs.fooddrink.iceCreamServing

fun main() {
  Arb.iceCreamServing().take(100).forEach(::println)
}
