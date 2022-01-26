// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {

  repositories {
    google()
    mavenCentral()
  }

  dependencies {
    classpath("com.android.tools.build:gradle:7.1.0")
    classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")

    // NOTE: Do not place your application dependencies here; they belong
    // in the individual module build.gradle files

    classpath("com.google.dagger:hilt-android-gradle-plugin:2.40.2")
  }
}

subprojects {

  repositories {
    google()
    mavenCentral()
  }

  afterEvaluate {
    val android = properties["android"]
    val dependencies = properties["dependencies"]

    (android as? com.android.build.gradle.BaseExtension)?.apply {
      setCompileSdkVersion(Version.compileSdk)
      buildToolsVersion = Version.buildTools

      defaultConfig {
        minSdk = Version.minSdk
        targetSdk = Version.targetSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
      }

      buildTypes {
        getByName("release") {
          isMinifyEnabled = true
          // isShrinkResources = true
          proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
      }

      compileOptions {
        isCoreLibraryDesugaringEnabled = true

        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
      }
    }
  }
}

tasks.create<Delete>("clean") {
  delete = setOf(
    rootProject.buildDir.absolutePath
  )
}
