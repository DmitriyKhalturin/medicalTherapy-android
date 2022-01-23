package org.medicine.ui.screen.therapyschedule.composable.form

import androidx.compose.runtime.Composable
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
  medicineId: Long,
  therapyOnRefresh: () -> Unit,
) {
  val viewModel = hiltViewModel<MedicineFormViewModel>().apply {
    destination = Destination.MedicineForm(therapyId, medicineId)
    obtainIntent(MedicalFormIntent.EnterScreen())
  }

  val uiState = viewModel.uiState

  MedicineFormView(
    uiState,
  )
}
