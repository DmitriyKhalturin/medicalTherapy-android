package org.medicine.ui.screen.dealform

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
import org.medicine.ui.screen.dealform.model.DealFormModel
import javax.inject.Inject

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for Medicine on 11.11.2021 0:40.
 */
@HiltViewModel
class DealFormViewModel @Inject constructor(
  private val repository: MedicalTherapyRepository,
) : NavigationViewModel<Destination.DealForm>(), IntentHandler<MedicalFormIntent<DealFormModel>> {

  var uiState by mutableStateOf<MedicalFormViewState<DealFormModel>>(MedicalFormViewState.Initial())

  override fun obtainIntent(intent: MedicalFormIntent<DealFormModel>) {
    launch {
      when (val state = uiState) {
        is MedicalFormViewState.Initial -> reduce(state, intent)
        else -> throw UnimplementedViewStateException(intent, state)
      }
    }
  }

  private suspend fun reduce(state: MedicalFormViewState.Initial<DealFormModel>, intent: MedicalFormIntent<DealFormModel>) {
  }
}
