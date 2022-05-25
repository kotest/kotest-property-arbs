plugins {
  kotlin("multiplatform").version("1.6.21")
  `java-library`
  `maven-publish`
  signing
}

repositories {
  mavenLocal()
  mavenCentral()
  maven {
    url = uri("https://oss.sonatype.org/content/repositories/snapshots")
  }
}

// this is the version used for building snapshots
// .GITHUB_RUN_NUMBER-snapshot will be appended
val snapshotBase = "2.0.0"

val githubRunNumber = System.getenv("GITHUB_RUN_NUMBER")

val snapshotVersion = when (githubRunNumber) {
  null -> "$snapshotBase-LOCAL"
  else -> "$snapshotBase.${githubRunNumber}-SNAPSHOT"
}

val releaseVersion = System.getenv("RELEASE_VERSION")

val isRelease = releaseVersion != null

group = "io.kotest.extensions"
version = releaseVersion ?: snapshotVersion

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
        api(libs.kotest.property)
        api(libs.kotest.property.datetime)
        implementation(libs.univocity.parsers)
        implementation(libs.kotlinx.datetime)
      }
    }
    val jvmTest by getting {
      dependencies {
        implementation(libs.kotest.runner.junit5)
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

// TODO: Remove me after https://youtrack.jetbrains.com/issue/KT-49109 is fixed
rootProject.plugins.withType<org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootPlugin> {
  rootProject.the<org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootExtension>().nodeVersion = "16.0.0"
}
