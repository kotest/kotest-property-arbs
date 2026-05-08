import org.jetbrains.kotlin.buildtools.api.ExperimentalBuildToolsApi
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion

plugins {
  kotlin("multiplatform").version("2.2.21")
  signing
  `maven-publish`
  alias(libs.plugins.nmcp)
}

group = "io.kotest"
version = Ci.version

kotlin {

  jvm {}
  js {
    browser()
    nodejs()
  }

  @OptIn(ExperimentalKotlinGradlePluginApi::class, ExperimentalBuildToolsApi::class)
  compilerOptions {
    apiVersion = KotlinVersion.KOTLIN_2_2
    languageVersion = KotlinVersion.KOTLIN_2_2
  }

  sourceSets {
    commonMain {
      dependencies {
        api(libs.kotest.property)
        api(libs.kotest.property.datetime)
        implementation(libs.kotlinx.datetime)
      }
    }
    jvmTest {
      dependencies {
        implementation(libs.kotest.runner.junit5)
      }
    }
    jsTest {
      dependencies {
        implementation(kotlin("test-js"))
      }
    }
  }
}

val generateArbResources =
  tasks.register<io.kotest.property.arbs.build.GenerateArbResourcesTask>("generateArbResources") {
    resourcesDir.set(layout.projectDirectory.dir("src/commonMain/resources"))
    outputDir.set(layout.buildDirectory.dir("generated/sources/arbs-data/commonMain/kotlin"))
  }

kotlin.sourceSets.named("commonMain") {
  kotlin.srcDir(generateArbResources.map { it.outputDir })
}

tasks.named("build") {
  dependsOn(generateArbResources)
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask<*>>().configureEach {
  dependsOn(generateArbResources)
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

publishing {
  publications.withType<MavenPublication>().configureEach {
    pom {
      name.set("kotest-property-arbs")
      description.set("Kotest property test arbitraries (people, places, products, ...)")
      url.set("https://github.com/kotest/kotest-property-arbs")

      scm {
        connection.set("scm:git:https://github.com/kotest/kotest-property-arbs/")
        developerConnection.set("scm:git:https://github.com/sksamuel/")
        url.set("https://github.com/kotest/kotest-property-arbs")
      }

      licenses {
        license {
          name.set("Apache-2.0")
          url.set("https://opensource.org/licenses/Apache-2.0")
        }
      }

      developers {
        developer {
          id.set("sksamuel")
          name.set("Stephen Samuel")
          email.set("sam@sksamuel.com")
        }
      }
    }
  }
}

pluginManager.withPlugin("org.jetbrains.kotlin.multiplatform") {
  val javadocJar by tasks.registering(Jar::class) {
    group = JavaBasePlugin.DOCUMENTATION_GROUP
    description = "Create Javadoc JAR"
    archiveClassifier.set("javadoc")
  }

  publishing.publications.withType<MavenPublication>().configureEach {
    artifact(javadocJar)
  }
}

val signingKey: String? by project
val signingPassword: String? by project

if (signingKey != null && signingPassword != null) {
  signing {
    useInMemoryPgpKeys(signingKey, signingPassword)
    sign(publishing.publications)
  }
}

// Workaround for https://youtrack.jetbrains.com/issue/KT-46466 — KMP publish tasks
// don't declare an explicit dependency on Sign tasks, causing flaky/failed publishes.
tasks.withType<AbstractPublishToMaven>().configureEach {
  mustRunAfter(tasks.withType<Sign>())
}

nmcp {
  publishAllPublicationsToCentralPortal {
    username = providers.environmentVariable("NEW_MAVEN_CENTRAL_USERNAME")
    password = providers.environmentVariable("NEW_MAVEN_CENTRAL_PASSWORD")
    publishingType = "USER_MANAGED"
  }
}
