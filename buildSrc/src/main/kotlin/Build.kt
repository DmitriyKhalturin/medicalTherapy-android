/*import org.gradle.api.plugins.ExtraPropertiesExtension
import kotlin.reflect.KVisibility
import kotlin.reflect.full.memberProperties*/

object Version {

  /*fun ext(properties: ExtraPropertiesExtension) = injectToExt(Version, properties)*/

  // Sdk and tools
  const val compileSdk = 31
  const val minSdk = 21
  const val targetSdk = 31

  const val buildTools = "31.0.0"

  const val kotlin = "1.6.10"
  const val hilt = "2.40.2"

  // App dependencies
  const val desugarJdk = "1.1.5"

  const val coroutines = "1.6.0"
  const val coreKtx = "1.7.0"

  const val appCompat = "1.4.1"
  const val material = "1.5.0"
  const val systemUIController = "0.17.0"
  const val lifecycle = "2.4.0"

  const val activityCompose = "1.4.0"
  const val navigationCompose = "2.4.0-rc01"
  const val hiltNavigationCompose = "1.0.0-rc01"
  const val constraintlayoutCompose = "1.0.0"
  const val compose = "1.1.0-rc02"

  const val persistentRoom = "2.4.1"

  const val gson = "2.8.6"

  /*private fun injectToExt(obj: Any, properties: ExtraPropertiesExtension) {
    println("***")
    obj::class.memberProperties
      .filterNot { it.isOpen }
      .filter { it.visibility == KVisibility.PUBLIC }
      .forEach { member ->
        if (member.name == "version") {
          val value = member.call(obj)
          if (value != null) {
            val name = "${obj::class.simpleName?.decapitalize()}Version"
            properties.set(name, value)
          }
        } else {
          val name = "${member.name}Version"
          val value = member.call()
          properties.set(name, value)
        }
      }

    obj::class.nestedClasses
      .mapNotNull { it.objectInstance }
      .forEach { objInstance ->
        injectToExt(objInstance, properties)
      }
  }*/
}
