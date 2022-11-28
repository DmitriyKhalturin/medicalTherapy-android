plugins {
  id("com.android.application")
  id("kotlin-android")
  id("kotlin-parcelize")
  id("kotlin-kapt")
  id("dagger.hilt.android.plugin")
}

android {

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
  implementation("androidx.appcompat:appcompat:${Version.appCompat}")
  implementation("com.google.android.material:material:${Version.material}")
  implementation("androidx.lifecycle:lifecycle-runtime-ktx:${Version.lifecycle}")

  inject(Dependency.coroutine)
  inject(Dependency.compose)
  inject(Dependency.hilt)
  inject(Dependency.accompanist)
  inject(Dependency.navigation)

  implementation(project(":schedule"))
  implementation(project(":source"))
  implementation(project(":tools"))

  inject(Dependency.junitTest)

  // https://stackoverflow.com/questions/72807725/noclassdeffounderror-could-not-initialize-class-androidx-customview-poolingcont
  debugImplementation("androidx.customview:customview-poolingcontainer:1.0.0")
}
