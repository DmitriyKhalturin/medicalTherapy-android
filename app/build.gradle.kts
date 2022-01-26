plugins {
  id("com.android.application")
  id("kotlin-android")
  id("kotlin-parcelize")
  id("kotlin-kapt")
  id("dagger.hilt.android.plugin")
}

android {

  defaultConfig {
    versionCode = Version.versionCode
    versionName = Version.versionName
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
}

dependencies {
  implementation("androidx.core:core-ktx:${Version.coreKtx}")

  inject(Dependency.coroutine)

  implementation("androidx.appcompat:appcompat:${Version.appCompat}")
  implementation("com.google.android.material:material:${Version.material}")
  implementation("com.google.accompanist:accompanist-systemuicontroller:${Version.systemUIController}")
  implementation("androidx.lifecycle:lifecycle-runtime-ktx:${Version.lifecycle}")

  inject(Dependency.compose)

  implementation("androidx.activity:activity-compose:${Version.activityCompose}")
  implementation("androidx.constraintlayout:constraintlayout-compose:${Version.constraintlayoutCompose}")
  implementation("androidx.compose.material:material-icons-extended:${Version.compose}")

  inject(Dependency.hilt)

  implementation("androidx.navigation:navigation-compose:${Version.navigationCompose}")
  implementation("androidx.hilt:hilt-navigation-compose:${Version.hiltNavigationCompose}")

  implementation(project(":schedule"))
  implementation(project(":source"))
  implementation(project(":tools"))

  testImplementation("junit:junit:4.13.2")
  testImplementation("androidx.test:core:1.4.0")
}
