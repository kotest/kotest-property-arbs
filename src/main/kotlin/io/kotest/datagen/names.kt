package io.kotest.datagen

data class FirstName(val name: String)
data class LastName(val name: String)
data class Name(val first: FirstName, val last: LastName)

object FirstNameProducer : Producer<FirstName> {
   private val names = lazy { loadResourceAsLines("/first_names.txt") }
   override fun produce(): FirstName {
      return FirstName(names.value.random())
   }
}

object LastNameProducer : Producer<LastName> {
   private val names = lazy { loadResourceAsLines("/last_names.txt") }
   override fun produce(): LastName {
      return LastName(names.value.random())
   }
}

val NameProducer = FirstNameProducer.zip(LastNameProducer) { a, b -> Name(a, b) }

fun main() {
   NameProducer.take(100).forEach(::println)
}