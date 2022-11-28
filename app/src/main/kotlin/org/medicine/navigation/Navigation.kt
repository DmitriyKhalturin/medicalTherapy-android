package org.medicine.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavGraph
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

/**
 * Documentation by [hilt](https://developer.android.com/jetpack/compose/libraries#hilt-navigation)
 */

@Composable
fun buildNavGraph(navController: NavController): NavGraph = remember {
  navController.createGraph(Route.Overview) {

    composable(Route.Overview) {
      OverviewScreen(navController = navController, viewModel = navigationViewModel())
    }

    composable(Route.TherapySchedule) {
      TherapyScheduleScreen(navController = navController, viewModel = navigationViewModel())
    }

    composable(Route.TherapyForm) {
      TherapyFormScreen(navController = navController, viewModel = navigationViewModel())
    }

    composable(Route.MedicineForm) {
      MedicineFormScreen(viewModel = navigationViewModel())
    }

    composable(Route.DealForm) {
      DealFormScreen(viewModel = navigationViewModel())
    }

    composable(Route.DaySchedule) {
      DayScheduleScreen(navController = navController, viewModel = navigationViewModel())
    }

    composable(Route.ApplicationInfo) {
      ApplicationInfoScreen(viewModel = navigationViewModel())
    }
  }
}
