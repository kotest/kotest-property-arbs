package io.kotest.property.arbs

import io.kotest.property.Arb
import io.kotest.property.arbitrary.of

object StreamingServices {
  val all = listOf(
    "Netflix",
    "Hulu",
    "Amazon Prime Video",
    "Disney+",
    "HBO Max",
    "Max",
    "Apple TV+",
    "Paramount+",
    "Peacock",
    "Discovery+",
    "ESPN+",
    "Crunchyroll",
    "Funimation",
    "YouTube TV",
    "Sling TV",
    "fuboTV",
    "Tubi",
    "Pluto TV",
    "Roku Channel",
    "Showtime",
    "Starz",
    "BritBox",
    "Acorn TV",
    "Shudder",
    "MUBI",
    "Criterion Channel",
    "Curiosity Stream",
    "Kanopy",
    "Hoopla",
    "Vudu",
    "Plex",
    "IMDb TV",
    "Freevee",
    "DAZN",
    "Rakuten Viki",
    "Crackle",
  )
}

fun Arb.Companion.streamingService(): Arb<String> = Arb.of(StreamingServices.all)
