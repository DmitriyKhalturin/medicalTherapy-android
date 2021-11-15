package org.medicine.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import java.time.LocalDate

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for Medicine on 11.11.2021 0:26.
 */
enum class RouteArguments(val arguments: List<NamedNavArgument>) {
  Id(listOf(
    navArgument(RouteArgumentsName.Id.name) {
      type = NavType.LongType
    }
  )),

  Date(listOf(
    navArgument(RouteArgumentsName.Date.name) {
      //type = NavType.fromArgType(type = "java.time.LocalDate", packageName = null)
      type = NavType.SerializableType(LocalDate::class.java)
      nullable = false
    }
  ));
}
