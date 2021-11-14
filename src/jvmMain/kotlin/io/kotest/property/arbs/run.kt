package io.kotest.property.arbs

import io.kotest.property.Arb
import io.kotest.property.arbitrary.take
import io.kotest.property.arbs.payments.transactions

fun main() {
  Arb.transactions().take(100).forEach(::println)
}
