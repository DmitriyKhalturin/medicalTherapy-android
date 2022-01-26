// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {

  repositories {
    google()
    mavenCentral()
  }

  dependencies {
    classpath("com.android.tools.build:gradle:${Version.gradle}")
    classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.kotlin}")

    // NOTE: Do not place your application dependencies here; they belong
    // in the individual module build.gradle files

    classpath("com.google.dagger:hilt-android-gradle-plugin:${Version.hilt}")
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

    (android as com.android.build.gradle.BaseExtension).apply {
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
