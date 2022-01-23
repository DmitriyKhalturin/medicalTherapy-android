package org.medicine.ui.screen.medicineform

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import dagger.hilt.android.lifecycle.HiltViewModel
import org.medicine.common.exception.UnimplementedViewStateException
import org.medicine.common.viewmodel.IntentHandler
import org.medicine.navigation.Destination
import org.medicine.navigation.viewmodel.NavigationViewModel
import org.medicine.source.repository.MedicalTherapyRepository
import org.medicine.ui.common.model.MedicalFormIntent
import org.medicine.ui.common.model.MedicalFormViewState
import org.medicine.ui.screen.medicineform.model.MedicineFormModel
import javax.inject.Inject

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for Medicine on 11.11.2021 0:39.
 */
@HiltViewModel
class MedicineFormViewModel @Inject constructor(
  private val repository: MedicalTherapyRepository,
) : NavigationViewModel<Destination.MedicineForm>(), IntentHandler<MedicalFormIntent<MedicineFormModel>> {

  var uiState by mutableStateOf<MedicalFormViewState<MedicineFormModel>>(MedicalFormViewState.Initial())
    private set

  override fun obtainIntent(intent: MedicalFormIntent<MedicineFormModel>) {
    launch {
      when (val state = uiState) {
        is MedicalFormViewState.Initial -> reduce(state, intent)
        else -> throw UnimplementedViewStateException(intent, state)
      }
    }
  }

  private suspend fun reduce(state: MedicalFormViewState.Initial<MedicineFormModel>, intent: MedicalFormIntent<MedicineFormModel>) {
  }
}
