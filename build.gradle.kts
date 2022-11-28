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

  tasks {
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
      kotlinOptions {
        jvmTarget = "1.8"
      }
    }
  }

  afterEvaluate {
    val android = properties["android"]
    val dependencies = properties["dependencies"]

    (android as com.android.build.gradle.BaseExtension).apply {
      val isApplicationModule = android is com.android.build.gradle.AppExtension

      setCompileSdkVersion(Version.targetSdk)

      defaultConfig {
        minSdk = Version.minSdk
        targetSdk = Version.targetSdk

        if (isApplicationModule) {
          versionCode = Version.versionCode
          versionName = Version.versionName
        }
      }

      buildTypes {
        getByName("release") {
          isMinifyEnabled = true
          if (isApplicationModule) {
            isShrinkResources = true
          }
          proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
      }

      compileOptions {
        isCoreLibraryDesugaringEnabled = true

        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
      }
    }

    (dependencies as DependencyHandler).apply {
      add("coreLibraryDesugaring", "com.android.tools:desugar_jdk_libs:${Version.desugarJdk}")
    }
  }
}

tasks.create<Delete>("clean") {
  delete = setOf(
    rootProject.buildDir.absolutePath
  )
}
