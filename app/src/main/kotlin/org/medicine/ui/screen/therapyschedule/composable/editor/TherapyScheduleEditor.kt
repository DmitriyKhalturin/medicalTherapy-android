package org.medicine.ui.screen.therapyschedule.composable.editor

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import org.medicine.navigation.Destination
import org.medicine.ui.common.model.MedicalFormIntent
import org.medicine.ui.screen.therapyform.TherapyFormView
import org.medicine.ui.screen.therapyform.TherapyFormViewModel

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
    obtainIntent(MedicalFormIntent.EnterScreen())
  }

  val uiState = viewModel.uiState

  TherapyFormView(
    uiState,
    { viewModel.obtainIntent(MedicalFormIntent.FillForm(it)) },
    { _, therapyForm -> viewModel.obtainIntent(MedicalFormIntent.CreateOrSaveObject(therapyId, therapyForm)) },
    { viewModel.obtainIntent(MedicalFormIntent.DeleteObject(therapyId)) },
    { saveOnSuccessful(therapyId) },
    { deleteOnSuccessful() },
  )
}
