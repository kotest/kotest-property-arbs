package io.kotest.property.arbs.travel

import io.kotest.property.Arb
import io.kotest.property.arbitrary.bind
import io.kotest.property.arbitrary.numericDoubles
import io.kotest.property.kotlinx.datetime.datetime
import kotlinx.datetime.LocalDateTime

data class AirJourney(
  val departure: Airport,
  val arrival: Airport,
  val departureTime: LocalDateTime,
  val arrivalTime: LocalDateTime,
  val distanceKm: Double,
  val airline: Airline,
)

fun Arb.Companion.airJourney() = Arb.bind(
  Arb.airport(),
  Arb.airport(),
  Arb.datetime(),
  Arb.datetime(),
  Arb.airline(),
  Arb.numericDoubles(100.0, 5000.0),
) { departure, arrival, dtime, atime, airline, distance ->
  AirJourney(departure, arrival, dtime, atime, distance, airline)
}
