package io.kotest.property.arbs.tube

import com.univocity.parsers.csv.CsvParser
import com.univocity.parsers.csv.CsvParserSettings
import io.kotest.property.Arb
import io.kotest.property.arbitrary.arbitrary
import io.kotest.property.arbitrary.flatMap
import io.kotest.property.arbitrary.map
import io.kotest.property.arbs.loadResource
import java.time.LocalDateTime
import kotlin.random.Random

data class Station(
  val id: Long,
  val latitude: Double,
  val longitude: Double,
  val name: String,
  val zone: Double,
  val lines: Int,
  val rail: Int
)

enum class FareMethod {
  Oyster, Contactless, NetworkRail, Mobile
}

data class Journey(
  val start: Station,
  val end: Station,
  val date: LocalDateTime,
  val durationMinutes: Int,
  val farePence: Int,
  val method: FareMethod
)

private val settings = CsvParserSettings().apply { this.isHeaderExtractionEnabled = true }

private val stations = CsvParser(settings)
  .parseAllRecords(loadResource("/tube/stations.csv"))
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

fun Arb.Companion.station() = arbitrary { stations.random(it.random) }

fun Arb.Companion.journey() = Arb.station().flatMap { stationA ->
  Arb.station().map { stationB ->

    val date = LocalDateTime.of(2020, 12, 31, 23, 59, 59)
      .minusDays(Random.nextLong(365 * 20))
      .minusSeconds(Random.nextLong(60 * 60 * 24))

    Journey(
      stationA,
      stationB,
      date,
      Random.nextInt(1, 59),
      Random.nextInt(0, 65) * 10,
      FareMethod.values().random()
    )
  }
}
