package org.medicine.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
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

/* https://developer.android.com/jetpack/compose/libraries#hilt-navigation */

@Composable
fun buildNavGraph(navController: NavController): NavGraph = remember {
  navController.createGraph(Route.Schedule.name) {

    composable(route = Route.Schedule.name) {
      val viewModel = hiltViewModel<ScheduleViewModel>()

      ScheduleScreen(viewModel)
    }

    composable(
      route = Route.Medication.name,
      arguments = RouteArguments.Medication.arguments,
    ) {
      val viewModel = hiltViewModel<MedicationViewModel>()

      MedicationScreen(viewModel)
    }

    composable(
      route = Route.Event.name,
      arguments = RouteArguments.Event.arguments,
    ) {
      val viewModel = hiltViewModel<EventViewModel>()

      EventScreen(viewModel)
    }

    composable(
      route = Route.DaySchedule.name,
      arguments = RouteArguments.DaySchedule.arguments,
    ) {
      val viewModel = hiltViewModel<DayScheduleViewModel>()

      DaySchedule(viewModel)
    }

    composable(route = Route.About.name) {
      val viewModel = hiltViewModel<AboutViewModel>()

      AboutScreen(viewModel)
    }
  }
}
