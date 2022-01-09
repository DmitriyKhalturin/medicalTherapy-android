package org.medicine.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import com.google.accompanist.systemuicontroller.SystemUiController
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
fun buildNavGraph(navController: NavController, systemUiController: SystemUiController): NavGraph = remember {
  navController.createGraph(Route.Overview) {

    composable(Route.Overview) {
      OverviewScreen(navController = navController, systemUiController = systemUiController, viewModel = hiltViewModel())
    }

    composable(Route.TherapySchedule) {
      TherapyScheduleScreen(viewModel = hiltViewModel())
    }

    composable(Route.TherapyForm) {
      TherapyFormScreen(navController = navController, systemUiController = systemUiController, viewModel = hiltViewModel())
    }

    composable(Route.MedicineForm) {
      MedicineFormScreen(viewModel = hiltViewModel())
    }

    composable(Route.DealForm) {
      DealFormScreen(viewModel = hiltViewModel())
    }

    composable(Route.DaySchedule) {
      DayScheduleScreen(viewModel = hiltViewModel())
    }

    composable(Route.ApplicationInfo) {
      ApplicationInfoScreen(systemUiController = systemUiController, viewModel = hiltViewModel())
    }
  }
}
