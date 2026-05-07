package io.kotest.property.arbs.tube

data class Station(
  val id: Long,
  val latitude: Double,
  val longitude: Double,
  val name: String,
  val zone: Double,
  val lines: Int,
  val rail: Int
)
