package io.kotest.datagen.tube

import com.univocity.parsers.csv.CsvParserSettings
import io.kotest.datagen.Producer
import io.kotest.datagen.loadResource

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
       loadResource("/tube/stations.csv"))
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

fun main() {
   StationProducer.take(100).forEach(::println)
}