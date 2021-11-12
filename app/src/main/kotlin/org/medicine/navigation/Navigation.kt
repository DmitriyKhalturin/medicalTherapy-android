package org.medicine.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.compose.composable
import androidx.navigation.createGraph
import org.medicine.ui.screen.*

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for Medicine on 11.11.2021 0:03.
 */

/* https://developer.android.com/jetpack/compose/libraries#hilt-navigation */

@Composable
fun buildNavGraph(navController: NavController): NavGraph = remember {
  navController.createGraph(Route.Schedule.name) {

    composable(route = Route.Schedule.name) {
      ScheduleScreen(hiltViewModel())
    }

    composable(
      route = Route.Medication.name,
      arguments = RouteArguments.Medication.arguments,
    ) {
      MedicationScreen(hiltViewModel())
    }

    composable(
      route = Route.Event.name,
      arguments = RouteArguments.Event.arguments,
    ) {
      EventScreen(hiltViewModel())
    }

    composable(
      route = Route.DaySchedule.name,
      arguments = RouteArguments.DaySchedule.arguments,
    ) {
      DaySchedule(hiltViewModel())
    }

    composable(route = Route.About.name) {
      AboutScreen(hiltViewModel())
    }
  }
}
