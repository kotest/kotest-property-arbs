repositories {
   mavenCentral()
   maven {
      url = uri("https://oss.sonatype.org/content/repositories/snapshots")
   }
}

plugins {
   java
   kotlin("multiplatform") version Libs.kotlinVersion
   `java-library`
   id("maven-publish")
   signing
   id("org.jetbrains.dokka") version Libs.dokkaVersion
}

repositories {
   mavenCentral()
   mavenLocal()
}

group = Libs.org
version = Ci.version

kotlin {
  targets {
    jvm {
      compilations.all {
        kotlinOptions {
          jvmTarget = "1.8"
        }
      }
    }
    js {
      browser()
      nodejs()
    }
  }

  sourceSets {
    val commonMain by getting {
      dependencies {
        implementation(Libs.stdLib)
        api(Libs.Kotest.property)
        api(Libs.Kotest.propertyDateTime)
        implementation("com.univocity:univocity-parsers:2.9.1")
        implementation(Libs.Kotlin.datetime)
      }
    }
    val jvmTest by getting {
      dependencies {
        implementation(Libs.Kotest.junit5)
      }
    }
  }
}

tasks.withType<Test> {
  useJUnitPlatform()
  filter {
    isFailOnNoMatchingTests = false
  }
  testLogging {
    showExceptions = true
    showStandardStreams = true
    events = setOf(
      org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED,
      org.gradle.api.tasks.testing.logging.TestLogEvent.PASSED,
      org.gradle.api.tasks.testing.logging.TestLogEvent.SKIPPED,
      org.gradle.api.tasks.testing.logging.TestLogEvent.STANDARD_OUT,
      org.gradle.api.tasks.testing.logging.TestLogEvent.STANDARD_ERROR
    )
    exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
  }
}

apply(from = "./publish-mpp.gradle.kts")
