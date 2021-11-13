package org.medicine.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for Medicine on 11.11.2021 0:26.
 */
enum class RouteArguments(val arguments: List<NamedNavArgument>) {
  Medicine(listOf(
    navArgument(RouteArgumentsName.Id.name) {
      type = NavType.LongType
      nullable = true
    }
  )),

  Deal(listOf(
    navArgument(RouteArgumentsName.Id.name) {
      type = NavType.LongType
      nullable = true
    }
  )),

  DaySchedule(listOf(
    navArgument(RouteArgumentsName.Date.name) {
      type = NavType.fromArgType(type = "java.time.LocalDate", packageName = null)
    }
  ));
}
