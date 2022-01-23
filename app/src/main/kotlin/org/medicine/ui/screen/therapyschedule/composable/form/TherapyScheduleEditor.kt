package org.medicine.ui.screen.therapyschedule.composable.form

import androidx.compose.runtime.Composable
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
fun TherapyScheduleEditor(
  therapyId: Long,
  saveOnSuccessful: (Long) -> Unit,
  deleteOnSuccessful: () -> Unit,
) {
  val viewModel = hiltViewModel<TherapyFormViewModel>().apply {
    destination = Destination.TherapyForm(therapyId)
    obtainIntent(TherapyFormIntent.EnterScreen)
  }

  val uiState = viewModel.uiState

  TherapyFormView(
    uiState,
    { viewModel.obtainIntent(TherapyFormIntent.FillTherapy(it)) },
    { _, therapyForm -> viewModel.obtainIntent(TherapyFormIntent.CreateOrSaveTherapy(therapyId, therapyForm)) },
    { viewModel.obtainIntent(TherapyFormIntent.DeleteTherapy(therapyId)) },
    { saveOnSuccessful(therapyId) },
    { deleteOnSuccessful() },
  )
}
