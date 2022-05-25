object Libs {

  const val kotlinVersion = "1.6.21"
  const val org = "io.kotest.extensions"
  const val stdLib = "org.jetbrains.kotlin:kotlin-stdlib-common:$kotlinVersion"

  object Kotlin {
    private const val version = "1.6.21"
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
    const val datetime = ""
  }

  object Kotest {
    private const val version = "5.3.0"
    const val property = "io.kotest:kotest-property:$version"
    const val propertyDateTime = "io.kotest.extensions:kotest-property-datetime:1.1.0"
    const val api = "io.kotest:kotest-framework-api:$version"
    const val junit5 = "io.kotest:kotest-runner-junit5:$version"
  }
}
