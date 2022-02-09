package io.kotest.property.arbs

import io.kotest.property.Arb
import io.kotest.property.arbitrary.GeoLocation
import io.kotest.property.arbitrary.arbitrary
import io.kotest.property.arbitrary.geoLocation
import io.kotest.property.arbitrary.ipAddressV4
import io.kotest.property.arbitrary.next

fun Arb.Companion.logins() = arbitrary { rs ->
  Login(
    timestamp = rs.random.nextLong(1000000000, 10000000000),
    username = Arb.usernames().next(rs),
    location = geoLocation().next(rs),
    ipAddress = ipAddressV4().next(rs),
    result = if (rs.random.nextBoolean()) "success" else listOf(
      "invalid password",
      "unknown username",
      "forbidden ipaddress",
      "banned username"
    ).random(rs.random)
  )
}

data class Login(
  val timestamp: Long,
  val username: Username,
  val location: GeoLocation,
  val ipAddress: String,
  val result: String,
)
