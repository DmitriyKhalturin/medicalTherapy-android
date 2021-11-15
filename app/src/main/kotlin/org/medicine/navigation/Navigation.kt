package org.medicine.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.compose.composable
import androidx.navigation.createGraph
import org.medicine.ui.screen.applicationinfo.ApplicationInfoScreen
import org.medicine.ui.screen.dayschedule.DayScheduleScreen
import org.medicine.ui.screen.dealform.DealFormScreen
import org.medicine.ui.screen.medicineform.MedicineFormScreen
import org.medicine.ui.screen.overview.OverviewScreen
import org.medicine.ui.screen.therapyform.TherapyFormScreen
import org.medicine.ui.screen.therapyschedule.TherapyScheduleScreen

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for Medicine on 11.11.2021 0:03.
 */

/* https://developer.android.com/jetpack/compose/libraries#hilt-navigation */

@Composable
fun buildNavGraph(navController: NavController): NavGraph = remember {
  navController.createGraph(Route.Overview.name) {

    composable(route = Route.Overview.name) {
      OverviewScreen(navController = navController, viewModel = hiltViewModel())
    }

    composable(route = Route.TherapySchedule.name) {
      TherapyScheduleScreen(viewModel = hiltViewModel())
    }

    composable(
      route = Route.TherapyForm.name,
      arguments = RouteArguments.Id.arguments,
    ) {
      TherapyFormScreen(viewModel = hiltViewModel())
    }

    composable(
      route = Route.MedicineForm.name,
      arguments = RouteArguments.Id.arguments,
    ) {
      MedicineFormScreen(viewModel = hiltViewModel())
    }

    composable(
      route = Route.DealForm.name,
      arguments = RouteArguments.Id.arguments,
    ) {
      DealFormScreen(viewModel = hiltViewModel())
    }

    composable(
      route = Route.DaySchedule.name,
      arguments = RouteArguments.Date.arguments,
    ) {
      DayScheduleScreen(viewModel = hiltViewModel())
    }

    composable(route = Route.ApplicationInfo.name) {
      ApplicationInfoScreen(viewModel = hiltViewModel())
    }
  }
}
