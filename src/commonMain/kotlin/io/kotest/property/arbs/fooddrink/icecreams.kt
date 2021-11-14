package io.kotest.property.arbs.fooddrink

import io.kotest.property.Arb
import io.kotest.property.arbitrary.bind
import io.kotest.property.arbitrary.enum
import io.kotest.property.arbitrary.list
import io.kotest.property.arbitrary.of

data class IceCreamFlavor(val value: String) {
  companion object {
    val all = listOf(
      IceCreamFlavor("Acai Berry"),
      IceCreamFlavor("Alaskan Mint"),
      IceCreamFlavor("Amaretto Cherry"),
      IceCreamFlavor("Apple Cinnamon"),
      IceCreamFlavor("Banana"),
      IceCreamFlavor("Banana Pudding"),
      IceCreamFlavor("Banana Split"),
      IceCreamFlavor("Banana Walnut"),
      IceCreamFlavor("Bananas Foster"),
      IceCreamFlavor("Birthday Cake"),
      IceCreamFlavor("Black Cherry"),
      IceCreamFlavor("Black Raspberry"),
      IceCreamFlavor("Black Walnut"),
      IceCreamFlavor("Blue Moon"),
      IceCreamFlavor("Blue Raspberry"),
      IceCreamFlavor("Blueberry Pie"),
      IceCreamFlavor("Brown Sugar"),
      IceCreamFlavor("Bubblegum"),
      IceCreamFlavor("Bumbleberry"),
      IceCreamFlavor("Butter Brickle"),
      IceCreamFlavor("Butter Pecan"),
      IceCreamFlavor("Buttered Almond"),
      IceCreamFlavor("Butterfinger"),
      IceCreamFlavor("Butterscotch"),
      IceCreamFlavor("Cake Batter"),
      IceCreamFlavor("Caramallow Chip"),
      IceCreamFlavor("Caramel Banana"),
      IceCreamFlavor("Carrot Cake"),
      IceCreamFlavor("Cherokee Gold"),
      IceCreamFlavor("Chiefs Chip"),
      IceCreamFlavor("Chocolate"),
      IceCreamFlavor("Chocolate Fudge"),
      IceCreamFlavor("Chocolate Swamp"),
      IceCreamFlavor("Cinnamon"),
      IceCreamFlavor("Cinnamon Turtle"),
      IceCreamFlavor("Coconut"),
      IceCreamFlavor("Coconut Ginger"),
      IceCreamFlavor("Coffee"),
      IceCreamFlavor("Confetti"),
      IceCreamFlavor("Cookie Dough"),
      IceCreamFlavor("Cotton Candy"),
      IceCreamFlavor("French Toast"),
      IceCreamFlavor("Fright Night"),
      IceCreamFlavor("Frog Pond"),
      IceCreamFlavor("Frog Tracks"),
      IceCreamFlavor("Fudge Swirl"),
      IceCreamFlavor("Georgia Peach"),
      IceCreamFlavor("Ginger"),
      IceCreamFlavor("Gorilla Vanilla"),
      IceCreamFlavor("Grape Escape"),
      IceCreamFlavor("Grape Nuts"),
      IceCreamFlavor("Green Tea"),
      IceCreamFlavor("Green Tree Frog"),
      IceCreamFlavor("Hazelnut Coffee"),
      IceCreamFlavor("Heath Bar"),
      IceCreamFlavor("Heavenly Hash"),
      IceCreamFlavor("Honey Espresso"),
      IceCreamFlavor("Honey Vanilla"),
      IceCreamFlavor("Huckleberry"),
      IceCreamFlavor("Hulk's Cream"),
      IceCreamFlavor("Irish Coffee"),
      IceCreamFlavor("Irish Cream"),
      IceCreamFlavor("Jack and Jill"),
      IceCreamFlavor("Jamaica Bay"),
      IceCreamFlavor("Key Lime Pie"),
      IceCreamFlavor("Lemon Coconut"),
      IceCreamFlavor("Lemon Crème"),
      IceCreamFlavor("Lemon Pie"),
      IceCreamFlavor("Leprechan Mint"),
      IceCreamFlavor("Licorice"),
      IceCreamFlavor("M&M"),
      IceCreamFlavor("Mango"),
      IceCreamFlavor("Maple Pecan"),
      IceCreamFlavor("Maple Walnut"),
      IceCreamFlavor("Mexican Coffee"),
      IceCreamFlavor("Orange Dream"),
      IceCreamFlavor("Oreo Mint"),
      IceCreamFlavor("Paradise"),
      IceCreamFlavor("PB&J"),
      IceCreamFlavor("Peppermint"),
      IceCreamFlavor("Pina Colada"),
      IceCreamFlavor("Pineapple"),
      IceCreamFlavor("Pistachio"),
      IceCreamFlavor("Princess Party"),
      IceCreamFlavor("Pumpkin"),
      IceCreamFlavor("Red Cream Soda"),
      IceCreamFlavor("Red Velvet"),
      IceCreamFlavor("Reese's Pieces"),
      IceCreamFlavor("Rum Raisin"),
      IceCreamFlavor("Sea Hunt"),
      IceCreamFlavor("Smores"),
      IceCreamFlavor("Snickers"),
      IceCreamFlavor("Spumoni"),
      IceCreamFlavor("Strawberry"),
      IceCreamFlavor("Tamarind"),
      IceCreamFlavor("Tiramisu Toffee"),
      IceCreamFlavor("Vanilla"),
      IceCreamFlavor("Vanilla Bean"),
      IceCreamFlavor("Watermelon Man"),
      IceCreamFlavor("Wild Cherry"),
      IceCreamFlavor("Witergreen"),
    )
  }
}

fun Arb.Companion.iceCreamFlavors() = Arb.of(IceCreamFlavor.all)

enum class ConeSize {
  Small, Medium, Large, Jumbo
}

enum class ConeType {
  Sugar, Waffle, Bowl, Cake
}

data class IceCreamServing(
  val scoops: Int,
  val coneType: ConeType,
  val size: ConeSize,
  val flavors: List<IceCreamFlavor>,
)

fun Arb.Companion.iceCreams() = Arb.bind(
  Arb.enum<ConeSize>(),
  Arb.enum<ConeType>(),
  Arb.list(Arb.iceCreamFlavors(), 1..5)
) { size, type, flavors ->
  IceCreamServing(flavors.size, type, size, flavors)
}
