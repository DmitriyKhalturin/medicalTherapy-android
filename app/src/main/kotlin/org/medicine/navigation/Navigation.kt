package org.medicine.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import org.medicine.navigation.bottomappbarstate.BottomAppBarState
import org.medicine.ui.screen.applicationinfo.ApplicationInfoBottomAppBarState
import org.medicine.ui.screen.applicationinfo.ApplicationInfoScreen
import org.medicine.ui.screen.dayschedule.DayScheduleBottomAppBarState
import org.medicine.ui.screen.dayschedule.DayScheduleScreen
import org.medicine.ui.screen.dealform.DealFormBottomAppBarState
import org.medicine.ui.screen.dealform.DealFormScreen
import org.medicine.ui.screen.medicineform.MedicineFormBottomAppBarState
import org.medicine.ui.screen.medicineform.MedicineFormScreen
import org.medicine.ui.screen.overview.OverviewBottomAppBarState
import org.medicine.ui.screen.overview.OverviewScreen
import org.medicine.ui.screen.therapyform.TherapyFormBottomAppBarState
import org.medicine.ui.screen.therapyform.TherapyFormScreen
import org.medicine.ui.screen.therapyschedule.TherapyScheduleBottomAppBarState
import org.medicine.ui.screen.therapyschedule.TherapyScheduleScreen

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for Medicine on 11.11.2021 0:03.
 */

/**
 * Documentation by [hilt](https://developer.android.com/jetpack/compose/libraries#hilt-navigation)
 */

@Composable
fun buildNavGraph(navController: NavController, setBottomAppBar: @Composable (BottomAppBarState) -> Unit): NavGraph = remember {
  navController.createGraph(Route.Overview) {

    composable(Route.Overview) {
      setBottomAppBar(OverviewBottomAppBarState())
      OverviewScreen(navController = navController, viewModel = hiltViewModel())
    }

    composable(Route.TherapySchedule) {
      setBottomAppBar(TherapyScheduleBottomAppBarState())
      TherapyScheduleScreen(viewModel = hiltViewModel())
    }

    composable(Route.TherapyForm) {
      setBottomAppBar(TherapyFormBottomAppBarState())
      TherapyFormScreen(navController = navController, viewModel = hiltViewModel())
    }

    composable(Route.MedicineForm) {
      setBottomAppBar(MedicineFormBottomAppBarState())
      MedicineFormScreen(viewModel = hiltViewModel())
    }

    composable(Route.DealForm) {
      setBottomAppBar(DealFormBottomAppBarState())
      DealFormScreen(viewModel = hiltViewModel())
    }

    composable(Route.DaySchedule) {
      setBottomAppBar(DayScheduleBottomAppBarState())
      DayScheduleScreen(viewModel = hiltViewModel())
    }

    composable(Route.ApplicationInfo) {
      setBottomAppBar(ApplicationInfoBottomAppBarState())
      ApplicationInfoScreen(viewModel = hiltViewModel())
    }
  }
}
