package io.kotest.property.arbs.tube

import io.kotest.property.Arb
import io.kotest.property.arbitrary.arbitrary
import io.kotest.property.arbitrary.flatMap
import io.kotest.property.arbitrary.map
import io.kotest.property.arbs.generated.tube.tubeStationsData
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import kotlin.random.Random

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

fun Arb.Companion.tubeStation() = arbitrary { tubeStationsData.random(it.random) }

private val baseInstant =
  LocalDateTime(2020, 12, 31, 23, 59, 59, 0).toInstant(TimeZone.UTC)

fun Arb.Companion.tubeJourney() = Arb.tubeStation().flatMap { stationA ->
  Arb.tubeStation().map { stationB ->

    val instant = baseInstant
      .minus(Random.nextLong(365 * 20), DateTimeUnit.DAY, TimeZone.UTC)
      .minus(Random.nextLong(60 * 60 * 24), DateTimeUnit.SECOND)
    val date = instant.toLocalDateTime(TimeZone.UTC)

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
