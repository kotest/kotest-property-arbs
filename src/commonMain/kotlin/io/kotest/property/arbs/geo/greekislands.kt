package io.kotest.property.arbs.geo

import io.kotest.property.Arb
import io.kotest.property.arbitrary.of

data class GreekIsland(
  val name: String,
  val capital: String,
  val seas: List<String>,
) {
  companion object {
    val Crete = GreekIsland("Crete", "Heraklion", listOf("Cretan Sea", "Libyan Sea", "Sea of Crete"))
    val Rhodes = GreekIsland("Rhodes", "Rhodes", listOf("Aegean Sea", "Mediterranean Sea"))
    val Corfu = GreekIsland("Corfu", "Corfu", listOf("Ionian Sea"))
    val Cephalonia = GreekIsland("Cephalonia", "Argostoli", listOf("Ionian Sea"))
    val Zakynthos = GreekIsland("Zakynthos", "Zakynthos", listOf("Ionian Sea"))
    val Lefkada = GreekIsland("Lefkada", "Lefkada", listOf("Ionian Sea"))
    val Ithaca = GreekIsland("Ithaca", "Vathy", listOf("Ionian Sea"))
    val Kythira = GreekIsland("Kythira", "Chora", listOf("Ionian Sea", "Sea of Crete"))
    val Mykonos = GreekIsland("Mykonos", "Mykonos Town", listOf("Aegean Sea"))
    val Santorini = GreekIsland("Santorini", "Fira", listOf("Aegean Sea", "Sea of Crete"))
    val Naxos = GreekIsland("Naxos", "Chora", listOf("Aegean Sea"))
    val Paros = GreekIsland("Paros", "Parikia", listOf("Aegean Sea"))
    val Andros = GreekIsland("Andros", "Chora", listOf("Aegean Sea"))
    val Tinos = GreekIsland("Tinos", "Tinos", listOf("Aegean Sea"))
    val Syros = GreekIsland("Syros", "Ermoupoli", listOf("Aegean Sea"))
    val Milos = GreekIsland("Milos", "Plaka", listOf("Aegean Sea", "Sea of Crete"))
    val Sifnos = GreekIsland("Sifnos", "Apollonia", listOf("Aegean Sea"))
    val Lesbos = GreekIsland("Lesbos", "Mytilene", listOf("Aegean Sea"))
    val Chios = GreekIsland("Chios", "Chios", listOf("Aegean Sea"))
    val Samos = GreekIsland("Samos", "Vathy", listOf("Aegean Sea", "Icarian Sea"))
    val Ikaria = GreekIsland("Ikaria", "Agios Kirykos", listOf("Aegean Sea", "Icarian Sea"))
    val Lemnos = GreekIsland("Lemnos", "Myrina", listOf("Aegean Sea", "Thracian Sea"))
    val Samothrace = GreekIsland("Samothrace", "Chora", listOf("Thracian Sea"))
    val Thasos = GreekIsland("Thasos", "Limenas", listOf("Thracian Sea"))
    val Kos = GreekIsland("Kos", "Kos", listOf("Aegean Sea"))
    val Patmos = GreekIsland("Patmos", "Chora", listOf("Aegean Sea", "Icarian Sea"))
    val Karpathos = GreekIsland("Karpathos", "Pigadia", listOf("Aegean Sea", "Mediterranean Sea"))
    val Kalymnos = GreekIsland("Kalymnos", "Pothia", listOf("Aegean Sea"))
    val Leros = GreekIsland("Leros", "Agia Marina", listOf("Aegean Sea"))
    val Symi = GreekIsland("Symi", "Symi", listOf("Aegean Sea"))
    val Astypalaia = GreekIsland("Astypalaia", "Chora", listOf("Aegean Sea"))
    val Skiathos = GreekIsland("Skiathos", "Skiathos", listOf("Aegean Sea"))
    val Skopelos = GreekIsland("Skopelos", "Skopelos", listOf("Aegean Sea"))
    val Skyros = GreekIsland("Skyros", "Chora", listOf("Aegean Sea"))
    val Alonnisos = GreekIsland("Alonnisos", "Patitiri", listOf("Aegean Sea"))
    val Aegina = GreekIsland("Aegina", "Aegina", listOf("Saronic Gulf"))
    val Hydra = GreekIsland("Hydra", "Hydra", listOf("Saronic Gulf", "Myrtoan Sea"))
    val Spetses = GreekIsland("Spetses", "Spetses", listOf("Argolic Gulf", "Myrtoan Sea"))
    val Poros = GreekIsland("Poros", "Poros", listOf("Saronic Gulf"))
    val Salamis = GreekIsland("Salamis", "Salamina", listOf("Saronic Gulf"))

    val all = listOf(
      Crete, Rhodes, Corfu, Cephalonia, Zakynthos, Lefkada, Ithaca, Kythira,
      Mykonos, Santorini, Naxos, Paros, Andros, Tinos, Syros, Milos, Sifnos,
      Lesbos, Chios, Samos, Ikaria, Lemnos, Samothrace, Thasos,
      Kos, Patmos, Karpathos, Kalymnos, Leros, Symi, Astypalaia,
      Skiathos, Skopelos, Skyros, Alonnisos,
      Aegina, Hydra, Spetses, Poros, Salamis,
    )
  }
}

fun Arb.Companion.greekIsland() = Arb.of(GreekIsland.all)
