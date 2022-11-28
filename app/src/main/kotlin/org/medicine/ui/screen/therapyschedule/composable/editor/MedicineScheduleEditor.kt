package org.medicine.ui.screen.therapyschedule.composable.editor

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import org.medicine.navigation.Destination
import org.medicine.ui.common.model.MedicalFormIntent
import org.medicine.ui.screen.medicineform.MedicineFormView
import org.medicine.ui.screen.medicineform.MedicineFormViewModel

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for MedicalTherapy on 15.01.2022 11:05.
 */

@Composable
fun MedicineScheduleEditor(
  therapyId: Long,
  _medicineId: Long? = null,
  therapyOnRefresh: () -> Unit,
) {
  val viewModel = hiltViewModel<MedicineFormViewModel>()

  LaunchedEffect(viewModel) {
    viewModel.run {
      destination = Destination.MedicineForm(therapyId, _medicineId)
      obtainIntent(MedicalFormIntent.EnterScreen())
    }
  }

  val uiState = viewModel.uiState

  MedicineFormView(
    uiState,
    { viewModel.obtainIntent(MedicalFormIntent.FillForm(it)) },
    { _, medicineId, medicineForm -> viewModel.obtainIntent(MedicalFormIntent.CreateOrSaveObject(medicineId, medicineForm))},
    { medicineId -> viewModel.obtainIntent(MedicalFormIntent.DeleteObject(medicineId)) },
    { therapyOnRefresh() },
    { therapyOnRefresh() },
  )
}
