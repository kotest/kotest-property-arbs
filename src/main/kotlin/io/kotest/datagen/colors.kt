package io.kotest.datagen

data class Color(val value: String)

object ColorProducer : Producer<Color> {
   private val colors = loadResourceAsLines("/colors.txt")
   override fun produce(): Color {
      return Color(colors.random())
   }
}