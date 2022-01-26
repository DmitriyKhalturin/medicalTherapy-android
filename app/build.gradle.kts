plugins {
  id("com.android.application")
  id("kotlin-android")
  id("kotlin-parcelize")
  id("kotlin-kapt")
  id("dagger.hilt.android.plugin")
}

android {

  defaultConfig {
    versionCode = 1
    versionName = "1.0.0"
  }

  buildFeatures {
    compose = true
  }

  composeOptions {
    kotlinCompilerExtensionVersion = Version.compose
  }

  packagingOptions {
    resources.excludes.add("/META-INF/{AL2.0,LGPL2.1}")
  }

  kotlinOptions.jvmTarget = "1.8"
}

dependencies {
  coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:${Version.desugarJdk}")

  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.coroutines}")
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.coroutines}")

  implementation("androidx.core:core-ktx:${Version.coreKtx}")

  implementation("androidx.appcompat:appcompat:${Version.appCompat}")
  implementation("com.google.android.material:material:${Version.material}")
  implementation("com.google.accompanist:accompanist-systemuicontroller:${Version.systemUIController}")
  implementation("androidx.lifecycle:lifecycle-runtime-ktx:${Version.lifecycle}")

  implementation("androidx.activity:activity-compose:${Version.activityCompose}")
  implementation("androidx.navigation:navigation-compose:${Version.navigationCompose}")
   implementation("androidx.constraintlayout:constraintlayout-compose:${Version.constraintlayoutCompose}")
  implementation("androidx.compose.material:material:${Version.compose}")
  implementation("androidx.compose.material:material-icons-extended:${Version.compose}")
  implementation("androidx.compose.ui:ui:${Version.compose}")
  implementation("androidx.compose.ui:ui-tooling-preview:${Version.compose}")
  debugImplementation("androidx.compose.ui:ui-tooling:${Version.compose}")

  implementation("com.google.dagger:hilt-android:${Version.hilt}")
  kapt("com.google.dagger:hilt-compiler:${Version.hilt}")
  implementation("androidx.hilt:hilt-navigation-compose:${Version.hiltNavigationCompose}")

  implementation(project(":schedule"))
  implementation(project(":source"))
  implementation(project(":tools"))

  testImplementation("junit:junit:4.13.2")
  testImplementation("androidx.test:core:1.4.0")
}
