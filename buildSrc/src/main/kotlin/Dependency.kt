/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for MedicalTherapy on 26.01.2022 20:28.
 */
object Dependency {

  val coroutine = DependencyConfig {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.coroutines}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.coroutines}")
  }

  val compose = DependencyConfig {
    implementation("androidx.compose.material:material:${Version.compose}")
    implementation("androidx.compose.ui:ui:${Version.compose}")
    implementation("androidx.compose.ui:ui-tooling-preview:${Version.compose}")
    debugImplementation("androidx.compose.ui:ui-tooling:${Version.compose}")
  }

  val hilt = DependencyConfig {
    implementation("com.google.dagger:hilt-android:${Version.hilt}")
    kapt("com.google.dagger:hilt-compiler:${Version.hilt}")
  }

  val room = DependencyConfig {
    implementation("androidx.room:room-ktx:${Version.persistentRoom}")
    implementation("androidx.room:room-runtime:${Version.persistentRoom}")
    kapt("androidx.room:room-compiler:${Version.persistentRoom}")
  }
}
