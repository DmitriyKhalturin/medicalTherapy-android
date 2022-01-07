package org.medicine.navigation

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import androidx.compose.runtime.Composable
import androidx.core.net.toUri
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.*
import androidx.navigation.compose.composable
import org.medicine.navigation.exception.DestinationShouldBeParcelable
import org.medicine.navigation.exception.IllegalDestination
import java.lang.reflect.Method

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for MedicalTherapy on 16.11.2021 17:44.
 */

/**
 * Maybe implement this [variant](https://stackoverflow.com/a/65878613/3998398)
 */

private const val DESTINATION_PARCELABLE = "DESTINATION_PARCELABLE"


inline fun NavController.createGraph(
  startDestination: Route,
  route: String? = null,
  builder: NavGraphBuilder.() -> Unit
): NavGraph = createGraph(startDestination.name, route, builder)

fun NavGraphBuilder.composable(
  route: Route,
  content: @Composable (NavBackStackEntry) -> Unit
) {
  composable(route = route.name, content = content)
}

fun NavController.navigate(
  destination: Destination,
  navOptions: NavOptions? = null,
  navigatorExtras: Navigator.Extras? = null
) {
  if (destination !is Parcelable) throw DestinationShouldBeParcelable(destination.route)

  navigate(destination.route.name, destination as Parcelable, navOptions, navigatorExtras)
}

fun <T> SavedStateHandle.destination() = try {
  requireNotNull(get<T>(DESTINATION_PARCELABLE))
} catch (e: Exception) {
  throw IllegalDestination()
}


private fun NavController.navigate(
  route: String,
  destinationParcelable: Parcelable,
  navOptions: NavOptions? = null,
  navigatorExtras: Navigator.Extras? = null
) {
  val request = NavDeepLinkRequest.Builder
    .fromUri(NavDestination.createRoute(route).toUri())
    .build()

  val deepLinkMatch = graph.matchDeepLink(request)

  if (deepLinkMatch != null) {
    val destination = deepLinkMatch.destination
    val arguments = destination.addInDefaultArgs(deepLinkMatch.matchingArgs) ?: Bundle()
    val node = deepLinkMatch.destination
    val intent = Intent().apply {
      setDataAndType(request.uri, request.mimeType)
      action = request.action
    }

    arguments.putParcelable(NavController.KEY_DEEP_LINK_INTENT, intent)

    arguments.putParcelable(DESTINATION_PARCELABLE, destinationParcelable)

    val declaredMethod: Method = this.javaClass.superclass.getDeclaredMethod(
      "navigate",
      NavDestination::class.java,
      Bundle::class.java,
      NavOptions::class.java,
      Navigator.Extras::class.java
    )

    declaredMethod.isAccessible = true

    declaredMethod.invoke(this, node, arguments, navOptions, navigatorExtras)
  } else {
    throw IllegalArgumentException(
      "Navigation destination that matches request $request cannot be found in the navigation graph $graph"
    )
  }
}
