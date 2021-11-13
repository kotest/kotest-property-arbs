repositories {
   mavenCentral()
   maven {
      url = uri("https://oss.sonatype.org/content/repositories/snapshots")
   }
}

plugins {
   java
   kotlin("multiplatform") version "1.5.31"
   `java-library`
   id("maven-publish")
   signing
   id("org.jetbrains.dokka") version "0.10.1"
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
        implementation(Libs.Kotest.property)
        implementation("com.univocity:univocity-parsers:2.9.1")
        implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.2.1")
        implementation("io.kotest:kotest-property-datetime:4.6.3")
      }
    }
    val jvmTest by getting {
      dependencies {
        implementation("io.kotest:kotest-runner-junit5-jvm:4.6.3")
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
