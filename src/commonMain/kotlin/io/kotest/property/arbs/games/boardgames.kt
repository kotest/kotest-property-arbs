package io.kotest.property.arbs.games

import io.kotest.property.Arb
import io.kotest.property.arbitrary.of

data class BoardGame(
  val name: String,
  val publisher: String,
  val type: String,
  val recommendedPlayers: String,
) {
  companion object {
    val all = listOf(
      BoardGame("Catan", "Kosmos", "Strategy", "3-4"),
      BoardGame("Carcassonne", "Hans im Glück", "Tile-placement", "2-5"),
      BoardGame("Ticket to Ride", "Days of Wonder", "Family", "2-5"),
      BoardGame("Pandemic", "Z-Man Games", "Cooperative", "2-4"),
      BoardGame("7 Wonders", "Repos Production", "Card drafting", "3-7"),
      BoardGame("Dominion", "Rio Grande Games", "Deck-building", "2-4"),
      BoardGame("Agricola", "Lookout Games", "Worker placement", "1-5"),
      BoardGame("Puerto Rico", "Ravensburger", "Strategy", "3-5"),
      BoardGame("Power Grid", "2F-Spiele", "Economic", "2-6"),
      BoardGame("Scythe", "Stonemaier Games", "Area-control", "1-5"),
      BoardGame("Terraforming Mars", "FryxGames", "Engine-building", "1-5"),
      BoardGame("Wingspan", "Stonemaier Games", "Engine-building", "1-5"),
      BoardGame("Spirit Island", "Greater Than Games", "Cooperative", "1-4"),
      BoardGame("Gloomhaven", "Cephalofair Games", "Campaign", "1-4"),
      BoardGame("Twilight Imperium", "Fantasy Flight Games", "Epic strategy", "3-6"),
      BoardGame("Risk", "Hasbro", "War", "2-6"),
      BoardGame("Monopoly", "Hasbro", "Family", "2-8"),
      BoardGame("Clue", "Hasbro", "Deduction", "3-6"),
      BoardGame("Scrabble", "Hasbro", "Word", "2-4"),
      BoardGame("Codenames", "Czech Games Edition", "Party", "2-8"),
      BoardGame("Azul", "Plan B Games", "Abstract", "2-4"),
      BoardGame("Splendor", "Space Cowboys", "Engine-building", "2-4"),
      BoardGame("Dixit", "Libellud", "Party", "3-6"),
      BoardGame("The Resistance: Avalon", "Indie Boards & Cards", "Social deduction", "5-10"),
      BoardGame("Secret Hitler", "Goat Wolf & Cabbage", "Social deduction", "5-10"),
      BoardGame("Blood on the Clocktower", "The Pandemonium Institute", "Social deduction", "5-20"),
      BoardGame("Root", "Leder Games", "Asymmetric", "2-4"),
      BoardGame("Brass: Birmingham", "Roxley Games", "Economic", "2-4"),
      BoardGame("Ark Nova", "Feuerland Spiele", "Strategy", "1-4"),
      BoardGame("Cascadia", "Flatout Games", "Tile-placement", "1-4"),
      BoardGame("Everdell", "Starling Games", "Worker placement", "1-4"),
      BoardGame("The Crew: Mission Deep Sea", "Kosmos", "Trick-taking", "3-5"),
      BoardGame("Hanabi", "Cocktail Games", "Cooperative", "2-5"),
      BoardGame("Cosmic Encounter", "Fantasy Flight Games", "Negotiation", "3-5"),
      BoardGame("Diplomacy", "Avalon Hill", "War", "2-7"),
    )
  }
}

fun Arb.Companion.boardGame() = Arb.of(BoardGame.all)
