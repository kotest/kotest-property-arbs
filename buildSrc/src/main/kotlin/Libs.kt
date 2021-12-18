object Libs {

  const val kotlinVersion = "1.5.31"
  const val org = "io.kotest.extensions"
  const val dokkaVersion = "0.10.1"
  const val stdLib = "org.jetbrains.kotlin:kotlin-stdlib-common:$kotlinVersion"

  object Kotlin {
    private const val version = "1.5.2"
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
    const val datetime = "org.jetbrains.kotlinx:kotlinx-datetime:0.2.1"
  }

  object Kotest {
    private const val version = "4.6.3"
    const val property = "io.kotest:kotest-property:$version"
    const val propertyDateTime = "io.kotest:kotest-property-datetime:$version"
    const val api = "io.kotest:kotest-framework-api:$version"
    const val junit5 = "io.kotest:kotest-runner-junit5:$version"
  }
}
