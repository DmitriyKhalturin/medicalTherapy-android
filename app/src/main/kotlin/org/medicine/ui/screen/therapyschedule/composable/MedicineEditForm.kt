package org.medicine.ui.screen.therapyschedule.composable

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import org.medicine.navigation.Destination
import org.medicine.ui.screen.medicineform.MedicineFormView
import org.medicine.ui.screen.medicineform.MedicineFormViewModel
import org.medicine.ui.screen.medicineform.model.MedicineFormIntent

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for MedicalTherapy on 15.01.2022 11:05.
 */

@Composable
fun MedicineEditForm(
  therapyId: Long,
  refreshTherapyCallback: () -> Unit,
) {
  val viewModel = hiltViewModel<MedicineFormViewModel>().apply {
    destination = Destination.MedicineForm(therapyId)
    obtainIntent(MedicineFormIntent.EnterScreen)
  }

  val uiState = viewModel.uiState

  MedicineFormView(
    uiState,
  )
}
