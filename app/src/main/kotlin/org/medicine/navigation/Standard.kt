package org.medicine.navigation

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import androidx.compose.runtime.Composable
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.*
import androidx.navigation.compose.composable
import org.medicine.navigation.exception.IllegalViewModelStoreOwner
import org.medicine.navigation.viewmodel.NavigationViewModel
import java.lang.reflect.Method

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for MedicalTherapy on 16.11.2021 17:44.
 */

/**
 * Maybe implement this [variant](https://stackoverflow.com/a/65878613/3998398)
 */

const val DESTINATION_PARCELABLE = "destination:parcelable"

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

@Composable
inline fun <reified T : Destination, reified VM : NavigationViewModel<T>> navigationViewModel(): VM {
  val viewModel = hiltViewModel<VM>()
  val backStackEntry = LocalViewModelStoreOwner.current

  if (backStackEntry is NavBackStackEntry) {
    val destination = backStackEntry.arguments?.getParcelable<T>(DESTINATION_PARCELABLE)

    if (destination != null) {
      viewModel.destination = destination
    }
  } else {
    throw IllegalViewModelStoreOwner()
  }

  return viewModel
}

fun NavController.navigate(
  destination: Destination,
  navOptions: NavOptions? = null,
  navigatorExtras: Navigator.Extras? = null
) {
  navigate(destination.route.name, destination as Parcelable, navOptions, navigatorExtras)
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
