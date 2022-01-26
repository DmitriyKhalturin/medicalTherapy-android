plugins {
  id("com.android.library")
  id("kotlin-android")
}

android {

  kotlinOptions.jvmTarget = "1.8"
}

dependencies {
  coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:${Version.desugarJdk}")

  implementation( "androidx.core:core-ktx:${Version.coreKtx}")

  testImplementation( "junit:junit:4.13.2")
  testImplementation( "androidx.test:core:1.4.0")
}
