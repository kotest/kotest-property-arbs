package io.kotest.datagen.wine

import io.kotest.datagen.Name
import io.kotest.datagen.NameProducer
import io.kotest.datagen.Producer
import io.kotest.datagen.loadResourceAsLines
import kotlin.random.Random

object VineyardProducer : Producer<Vineyard> {
   private val vineyards = loadResourceAsLines("/wine/vineyards.txt")
   override fun produce(): Vineyard {
      return Vineyard(vineyards.random())
   }
}

object RegionProducer : Producer<Region> {
   private val regions = loadResourceAsLines("/wine/region.txt")
   override fun produce(): Region {
      return Region(regions.random())
   }
}

object WineryProducer : Producer<Winery> {
   private val regions = loadResourceAsLines("/wine/winery.txt")
   override fun produce(): Winery {
      return Winery(regions.random())
   }
}

object VarietyProducer : Producer<Variety> {
   private val regions = loadResourceAsLines("/wine/variety.txt")
   override fun produce(): Variety {
      return Variety(regions.random())
   }
}

object WineProducer : Producer<Wine> {

   private val producer = VineyardProducer.zip(
       RegionProducer,
       WineryProducer,
       VarietyProducer) { vineyard, region, winery, variety ->
      Wine(vineyard, variety, winery, region, Random.nextInt(1920, 2020))
   }

   override fun produce(): Wine = producer.produce()
}

object WineReviewProducer : Producer<WineReview> {
   private val producer = WineProducer.zip(NameProducer) { wine, name ->
      WineReview(wine, Random.nextDouble(0.1, 5.0), name)
   }
   override fun produce(): WineReview = producer.produce()
}

data class Wine(val vineyard: Vineyard, val variety: Variety, val winery: Winery, val region: Region, val year: Int)
data class WineReview(val wine: Wine, val rating: Double, val name: Name)
data class Vineyard(val value: String)
data class Variety(val value: String)
data class Winery(val value: String)
data class Region(val value: String)