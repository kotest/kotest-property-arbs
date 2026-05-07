@file:Suppress("UnstableApiUsage")

rootProject.name = "kotest-property-arbs"

pluginManagement {
  repositories {
    mavenCentral()
    gradlePluginPortal()
  }
}

enableFeaturePreview("STABLE_CONFIGURATION_CACHE")

dependencyResolutionManagement {
  repositories {
    mavenCentral()
    mavenLocal()
  }
}
