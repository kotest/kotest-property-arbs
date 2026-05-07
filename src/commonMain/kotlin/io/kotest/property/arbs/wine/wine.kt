package io.kotest.property.arbs.wine

import io.kotest.property.Arb
import io.kotest.property.arbitrary.flatMap
import io.kotest.property.arbitrary.map
import io.kotest.property.arbitrary.of
import io.kotest.property.arbs.Name
import io.kotest.property.arbs.generated.wine.wineRegionData
import io.kotest.property.arbs.generated.wine.wineVarietyData
import io.kotest.property.arbs.generated.wine.wineVineyardsData
import io.kotest.property.arbs.generated.wine.wineWineryData
import io.kotest.property.arbs.name
import kotlin.random.Random

fun Arb.Companion.vineyards() = Arb.of(wineVineyardsData).map { Vineyard(it) }
fun Arb.Companion.wineRegions() = Arb.of(wineRegionData).map { WineRegion(it) }
fun Arb.Companion.wineries() = Arb.of(wineWineryData).map { Winery(it) }
fun Arb.Companion.wineVarities() = Arb.of(wineVarietyData).map { WineVariety(it) }

fun Arb.Companion.wines() = vineyards().flatMap { vineyard ->
  wineRegions().flatMap { region ->
    wineVarities().flatMap { variety ->
      wineries().map { winery ->
        Wine(vineyard, variety, winery, region, Random.nextInt(1920, 2020))
      }
    }
  }
}

fun Arb.Companion.wineReviews() = wines().flatMap { wine ->
  name().map { name ->
    WineReview(wine, Random.nextDouble(0.1, 5.0), name)
  }
}

data class Wine(
  val vineyard: Vineyard,
  val variety: WineVariety,
  val winery: Winery,
  val region: WineRegion,
  val year: Int
)

data class WineReview(val wine: Wine, val rating: Double, val name: Name)
data class Vineyard(val value: String)
data class WineVariety(val value: String)
data class Winery(val value: String)
data class WineRegion(val value: String)
