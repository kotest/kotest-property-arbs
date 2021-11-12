package io.kotest.property.arbs.wine

import io.kotest.property.Arb
import io.kotest.property.arbitrary.flatMap
import io.kotest.property.arbitrary.map
import io.kotest.property.arbitrary.of
import io.kotest.property.arbitrary.take
import io.kotest.property.arbs.Name
import io.kotest.property.arbs.loadResourceAsLines
import io.kotest.property.arbs.name
import kotlin.random.Random

private val vineyards = lazy { loadResourceAsLines("/wine/vineyards.txt") }
private val regions = lazy { loadResourceAsLines("/wine/region.txt") }
private val wineries = lazy { loadResourceAsLines("/wine/winery.txt") }
private val varities = lazy { loadResourceAsLines("/wine/variety.txt") }

fun Arb.Companion.vineyards() = Arb.of(vineyards.value).map { Vineyard(it) }
fun Arb.Companion.wineRegions() = Arb.of(regions.value).map { WineRegion(it) }
fun Arb.Companion.wineries() = Arb.of(wineries.value).map { Winery(it) }
fun Arb.Companion.wineVarities() = Arb.of(varities.value).map { WineVariety(it) }

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

fun main() {
  Arb.wineReviews().take(100).forEach(::println)
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
