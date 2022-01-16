package org.medicine.ui.screen.therapyschedule.composable

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
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
  therapyId: Long,
  savingSuccessfulCallback: (Long) -> Unit,
  deletingSuccessfulCallback: () -> Unit,
) {
  val viewModel = hiltViewModel<TherapyFormViewModel>().apply {
    destination = Destination.TherapyForm(therapyId)
  }

  LaunchedEffect(viewModel) {
    viewModel.obtainIntent(TherapyFormIntent.EnterScreen)
  }

  val uiState = viewModel.uiState

  TherapyFormView(
    uiState,
    { viewModel.obtainIntent(TherapyFormIntent.FillTherapy(it)) },
    { _, therapyForm -> viewModel.obtainIntent(TherapyFormIntent.CreateOrSaveTherapy(therapyId, therapyForm)) },
    { viewModel.obtainIntent(TherapyFormIntent.DeleteTherapy(therapyId)) },
    { savingSuccessfulCallback(therapyId) },
    { deletingSuccessfulCallback() },
  )
}
