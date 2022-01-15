package org.medicine.ui.screen.therapyschedule.composable

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
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
) {
  val viewModel = hiltViewModel<TherapyFormViewModel>()
  val uiState = viewModel.uiState

  TherapyFormView(
    navController,
    uiState,
    { viewModel.obtainIntent(TherapyFormIntent.FillTherapy(it)) },
    { therapyId, therapyForm -> viewModel.obtainIntent(TherapyFormIntent.CreateOrSaveTherapy(therapyId, therapyForm)) },
    { therapyId -> viewModel.obtainIntent(TherapyFormIntent.DeleteTherapy(therapyId)) }
  )
}
