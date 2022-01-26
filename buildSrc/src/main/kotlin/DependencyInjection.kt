import org.gradle.api.artifacts.dsl.DependencyHandler

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for MedicalTherapy on 26.01.2022 20:51.
 */

fun DependencyHandler.inject(config: DependencyConfig) = with(config) {

  implementations.forEach {
    add("implementation", it)
  }

  debugImplementations.forEach {
    add("debugImplementation", it)
  }

  kapts.forEach {
    add("kapt", it)
  }
}
