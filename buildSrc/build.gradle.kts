plugins {
  `kotlin-dsl`
}

dependencies {
  implementation("com.univocity:univocity-parsers:2.9.1")
  testImplementation("org.junit.jupiter:junit-jupiter:5.9.0")
}

tasks.test {
  useJUnitPlatform()
}
