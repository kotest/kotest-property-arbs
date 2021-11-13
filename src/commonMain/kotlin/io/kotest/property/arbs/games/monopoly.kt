package io.kotest.property.arbs.games

data class MonopolyProperty(
  val name: String,
  val color: String,
  val space: Int,
  val price: Int,
  val rent: Int,
) {
  companion object {
    val all = listOf(
      MonopolyProperty("Mediterranean Avenue", "Brown", 1, 60, 50),
      MonopolyProperty("Baltic Avenue", "Brown", 3, 60, 50),
      MonopolyProperty("Reading Railroad", "None", 5, 200, 0),
      MonopolyProperty("Oriental Avenue", "LightBlue", 6, 100, 50),
      MonopolyProperty("Vermont Avenue", "LightBlue", 8, 100, 50),
      MonopolyProperty("Connecticut Avenue", "LightBlue", 9, 120, 50),
      MonopolyProperty("St. Charles Place", "Pink", 11, 140, 100),
      MonopolyProperty("Electric Company", "None", 12, 150, 0),
      MonopolyProperty("States Avenue", "Pink", 13, 140, 100),
      MonopolyProperty("Virginia Avenue", "Pink", 14, 160, 100),
      MonopolyProperty("Pennsylvania Railroad", "None", 15, 200, 0),
      MonopolyProperty("St. James Place", "Orange", 16, 180, 100),
      MonopolyProperty("Tennessee Avenue", "Orange", 18, 180, 100),
      MonopolyProperty("New York Avenue", "Orange", 19, 200, 100),
      MonopolyProperty("Kentucky Avenue", "Red", 21, 220, 150),
      MonopolyProperty("Indiana Avenue", "Red", 23, 220, 150),
      MonopolyProperty("Illinois Avenue", "Red", 24, 240, 150),
      MonopolyProperty("B. & O. Railroad", "None", 25, 200, 0),
      MonopolyProperty("Atlantic Avenue", "Yellow", 26, 260, 150),
      MonopolyProperty("Ventnor Avenue", "Yellow", 27, 260, 150),
      MonopolyProperty("Water Works", "None", 28, 150, 0),
      MonopolyProperty("Marvin Gardens", "Yellow", 29, 280, 150),
      MonopolyProperty("Pacific Avenue", "Green", 31, 300, 200),
      MonopolyProperty("North Carolina Avenue", "Green", 32, 300, 200),
      MonopolyProperty("Pennsylvania Avenue", "Green", 34, 320, 200),
      MonopolyProperty("Short Line,Railroad", "None", 35, 200, 0),
      MonopolyProperty("Park Place", "Blue", 37, 350, 200),
      MonopolyProperty("Boardwalk", "Blue", 39, 400, 200),
    )
  }
}
