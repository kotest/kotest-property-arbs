package io.kotest.property.arbs.tube

import com.univocity.parsers.csv.CsvParserSettings
import io.kotest.property.arbs.Producer
import io.kotest.property.arbs.loadResource
import java.time.LocalDateTime
import kotlin.random.Random

data class Station(val id: Long,
                   val latitude: Double,
                   val longitude: Double,
                   val name: String,
                   val zone: Double,
                   val lines: Int,
                   val rail: Int)

object StationProducer : Producer<Station> {

   private val settings = CsvParserSettings().apply { this.isHeaderExtractionEnabled = true }
   private val stations = com.univocity.parsers.csv.CsvParser(settings).parseAllRecords(
       loadResource("/tube/stations.csv")
   )
       .map {
          Station(
              it.getLong("id"),
              it.getDouble("latitude"),
              it.getDouble("longitude"),
              it.getString("name"),
              it.getDouble("zone"),
              it.getInt("total_lines"),
              it.getInt("rail")
          )
       }

   override fun produce(): Station = stations.random()
}

enum class FareMethod {
   Oyster, Contactless, NetworkRail, Mobile
}

data class Journey(val start: Station,
                   val end: Station,
                   val date: LocalDateTime,
                   val durationMinutes: Int,
                   val farePence: Int,
                   val method: FareMethod
)

object JourneyProducer : Producer<Journey> {

   private val stations = StationProducer

   override fun produce(): Journey {

      val date = LocalDateTime.of(2020, 12, 31, 23, 59, 59)
          .minusDays(Random.nextLong(365 * 20))
          .minusSeconds(Random.nextLong(60 * 60 * 24))

      return Journey(
          StationProducer.produce(),
          StationProducer.produce(),
          date,
          Random.nextInt(1, 59),
          Random.nextInt(0, 65) * 10,
          FareMethod.values().random()
      )
   }

}

fun main() {
   StationProducer.take(25).forEach(::println)
   JourneyProducer.take(25).forEach(::println)
}