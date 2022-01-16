package org.medicine.ui.screen.therapyschedule.composable

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import org.medicine.navigation.Destination
import org.medicine.ui.screen.therapyform.TherapyFormView
import org.medicine.ui.screen.therapyform.TherapyFormViewModel
import org.medicine.ui.screen.therapyform.model.TherapyFormIntent

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for MedicalTherapy on 15.01.2022 11:05.
 */

@Composable
fun TherapyEditForm(
  navController: NavController,
  therapyId: Long,
) {
  val viewModel = hiltViewModel<TherapyFormViewModel>()

  viewModel.destination = Destination.TherapyForm(therapyId)
  viewModel.obtainIntent(TherapyFormIntent.EnterScreen)

  val uiState = viewModel.uiState

  TherapyFormView(
    uiState,
    { viewModel.obtainIntent(TherapyFormIntent.FillTherapy(it)) },
    { _, therapyForm -> viewModel.obtainIntent(TherapyFormIntent.CreateOrSaveTherapy(therapyId, therapyForm)) },
    { viewModel.obtainIntent(TherapyFormIntent.DeleteTherapy(therapyId)) },
    {},
    {},
  )
}
