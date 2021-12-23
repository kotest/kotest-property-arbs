package io.kotest.property.arbs

import io.kotest.property.Arb
import io.kotest.property.arbitrary.take

fun main() {
  Arb.usernames().take(100).forEach(::println)
}
