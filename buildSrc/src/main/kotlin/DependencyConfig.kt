/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for MedicalTherapy on 26.01.2022 20:31.
 */
class DependencyConfig(init: DependencyConfig.() -> Unit) {

  val implementations = mutableListOf<String>()
  val debugImplementations = mutableListOf<String>()
  val kapts = mutableListOf<String>()

  fun implementation(value: String) {
    implementations.add(value)
  }

  fun debugImplementation(value: String) {
    debugImplementations.add(value)
  }

  fun kapt(value: String) {
    kapts.add(value)
  }

  init {
    init()
  }
}
