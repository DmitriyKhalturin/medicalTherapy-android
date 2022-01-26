plugins {
  id("com.android.library")
  id("kotlin-android")
}

dependencies {
  implementation( "androidx.core:core-ktx:${Version.coreKtx}")

  testImplementation( "junit:junit:4.13.2")
  testImplementation( "androidx.test:core:1.4.0")
}
