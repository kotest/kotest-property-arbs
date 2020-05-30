package io.kotest.datagen

data class FirstName(val name: String)
data class LastName(val name: String)
data class Name(val first: FirstName, val last: LastName)

object FirstNameProducer : Producer<FirstName> {
   private val names = lazy { javaClass.getResourceAsStream("/first_names.txt").bufferedReader().readLines() }
   override fun produce(): FirstName {
      return FirstName(names.value.random())
   }
}

object LastNameProducer : Producer<LastName> {
   private val names = lazy { javaClass.getResourceAsStream("/last_names.txt").bufferedReader().readLines() }
   override fun produce(): LastName {
      return LastName(names.value.random())
   }
}

val NameProducer = FirstNameProducer.zip(LastNameProducer) { a, b -> Name(a, b) }