package io.kotest.datagen

object HarryPotterCharacterProducer : Producer<Name> {
   private val first = loadResourceAsLines("/harrypotter_first_names.csv")
   private val last = loadResourceAsLines("/harrypotter_last_names.csv")
   override fun produce(): Name = Name(FirstName(first.random()), LastName(last.random()))
}

fun main() {
   HarryPotterCharacterProducer.take(100).forEach(::println)
}