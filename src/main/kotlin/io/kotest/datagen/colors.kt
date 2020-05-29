package io.kotest.datagen

data class Color(val value: String)

object ColorProducer : Producer<Color> {
   private val colors = javaClass.getResourceAsStream("/colors.txt").bufferedReader().readLines()
   override fun produce(): Color {
      return Color(colors.random())
   }
}