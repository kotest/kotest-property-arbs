object Libs {

  const val kotlinVersion = "1.6.10"
  const val org = "io.kotest.extensions"
  const val dokkaVersion = "0.10.1"
  const val stdLib = "org.jetbrains.kotlin:kotlin-stdlib-common:$kotlinVersion"

  object Kotlin {
    private const val version = "1.6.0-RC3"
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
    const val datetime = "org.jetbrains.kotlinx:kotlinx-datetime:0.3.1"
  }

  object Kotest {
    private const val version = "5.0.3"
    const val property = "io.kotest:kotest-property:$version"
    const val propertyDateTime = "io.kotest:kotest-property-datetime:1.1.0"
    const val api = "io.kotest:kotest-framework-api:$version"
    const val junit5 = "io.kotest:kotest-runner-junit5:$version"
  }
}
