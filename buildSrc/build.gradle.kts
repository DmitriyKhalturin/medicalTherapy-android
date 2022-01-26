repositories {
  google()
  mavenCentral()
}

plugins {
  `java-gradle-plugin`
  `kotlin-dsl`
}

dependencies {
  implementation(kotlin("script-runtime"))
  implementation(kotlin("gradle-plugin"))
  implementation("com.android.tools.build:gradle:7.1.0")
}
