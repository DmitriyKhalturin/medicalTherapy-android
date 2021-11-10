package org.medicine.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.compose.composable
import androidx.navigation.createGraph
import org.medicine.ui.screen.*
import org.medicine.viewmodel.*

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for Medicine on 11.11.2021 0:03.
 */

@Composable
fun buildNavGraph(navController: NavController): NavGraph = remember {
  navController.createGraph(Route.Schedule.name) {

    composable(route = Route.Schedule.name) {
      val viewModel = viewModel<ScheduleViewModel>()

      ScheduleScreen()
    }

    composable(
      route = Route.Medication.name,
      arguments = RouteArguments.Medication.arguments,
    ) { backStackEntry ->
      val medicationId = requireNotNull(backStackEntry.arguments?.getLong(RouteArgumentsName.Id.name))
      val viewModel = viewModel<MedicationViewModel>()

      MedicationScreen()
    }

    composable(
      route = Route.Event.name,
      arguments = RouteArguments.Event.arguments,
    ) { backStackEntry ->
      val eventId = requireNotNull(backStackEntry.arguments?.getLong(RouteArgumentsName.Id.name))
      val viewModel = viewModel<EventViewModel>()

      EventScreen()
    }

    composable(
      route = Route.DaySchedule.name,
      arguments = RouteArguments.DaySchedule.arguments,
    ) { backStackEntry ->
      val scheduleDate = requireNotNull(backStackEntry.arguments?.getLong(RouteArgumentsName.Date.name))
      val viewModel = viewModel<DayScheduleViewModel>()

      DaySchedule()
    }

    composable(route = Route.About.name) {
      val viewModel = viewModel<AboutViewModel>()

      AboutScreen()
    }
  }
}
