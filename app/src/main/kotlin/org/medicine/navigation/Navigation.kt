package org.medicine.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import org.medicine.navigation.bottomappbarstate.BottomAppBarState
import org.medicine.ui.screen.applicationinfo.ApplicationInfoScreen
import org.medicine.ui.screen.applicationinfo.bottomappbarstate.ApplicationInfoBottomAppBarState
import org.medicine.ui.screen.dayschedule.DayScheduleScreen
import org.medicine.ui.screen.dayschedule.bottomappbarstate.DayScheduleBottomAppBarState
import org.medicine.ui.screen.dealform.DealFormScreen
import org.medicine.ui.screen.dealform.bottomappbarstate.DealFormBottomAppBarState
import org.medicine.ui.screen.medicineform.MedicineFormScreen
import org.medicine.ui.screen.medicineform.bottomappbarstate.MedicineFormBottomAppBarState
import org.medicine.ui.screen.overview.OverviewScreen
import org.medicine.ui.screen.overview.bottomappbarstate.OverviewBottomAppBarState
import org.medicine.ui.screen.therapyform.TherapyFormScreen
import org.medicine.ui.screen.therapyform.bottomappbarstate.TherapyFormBottomAppBarState
import org.medicine.ui.screen.therapyschedule.TherapyScheduleScreen
import org.medicine.ui.screen.therapyschedule.bottomappbarstate.TherapyScheduleBottomAppBarState

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for Medicine on 11.11.2021 0:03.
 */

/**
 * Documentation by [hilt](https://developer.android.com/jetpack/compose/libraries#hilt-navigation)
 */

@Composable
fun buildNavGraph(navController: NavController, callback: @Composable (BottomAppBarState) -> Unit): NavGraph = remember {
  navController.createGraph(Route.Overview) {

    composable(Route.Overview) {
      callback(OverviewBottomAppBarState(navController))
      OverviewScreen(navController = navController, viewModel = hiltViewModel())
    }

    composable(Route.TherapySchedule) {
      callback(TherapyScheduleBottomAppBarState())
      TherapyScheduleScreen(viewModel = hiltViewModel())
    }

    composable(Route.TherapyForm) {
      callback(TherapyFormBottomAppBarState())
      TherapyFormScreen(navController = navController, viewModel = hiltViewModel())
    }

    composable(Route.MedicineForm) {
      callback(MedicineFormBottomAppBarState())
      MedicineFormScreen(viewModel = hiltViewModel())
    }

    composable(Route.DealForm) {
      callback(DealFormBottomAppBarState())
      DealFormScreen(viewModel = hiltViewModel())
    }

    composable(Route.DaySchedule) {
      callback(DayScheduleBottomAppBarState())
      DayScheduleScreen(viewModel = hiltViewModel())
    }

    composable(Route.ApplicationInfo) {
      callback(ApplicationInfoBottomAppBarState(navController))
      ApplicationInfoScreen(viewModel = hiltViewModel())
    }
  }
}
