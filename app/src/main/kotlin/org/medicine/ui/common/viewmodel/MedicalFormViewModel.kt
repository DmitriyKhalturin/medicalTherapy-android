package org.medicine.ui.common.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import org.medicine.common.viewmodel.IntentHandler
import org.medicine.navigation.Destination
import org.medicine.navigation.viewmodel.NavigationViewModel
import org.medicine.ui.common.model.MedicalFormIntent
import org.medicine.ui.common.model.MedicalFormViewState

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for MedicalTherapy on 27.01.2022 0:30.
 */
abstract class MedicalFormViewModel<D: Destination, M: Any> : NavigationViewModel<D>(), IntentHandler<MedicalFormIntent<M>> {

  var uiState by mutableStateOf<MedicalFormViewState<M>>(MedicalFormViewState.Initial())
    protected set

  override fun obtainIntent(intent: MedicalFormIntent<M>) {
    launch {
      val state = uiState

      when (intent) {
        is MedicalFormIntent.EnterScreen -> enterScreen(state)
        is MedicalFormIntent.FillForm -> fillForm(state, intent)
        is MedicalFormIntent.CreateOrSaveObject -> createOrSaveObject(state, intent)
        is MedicalFormIntent.DeleteObject -> deleteObject(state, intent)
      }
    }
  }

  abstract suspend fun enterScreen(state: MedicalFormViewState<M>)

  abstract suspend fun fillForm(state: MedicalFormViewState<M>, intent: MedicalFormIntent<M>)

  abstract suspend fun createOrSaveObject(state: MedicalFormViewState<M>, intent: MedicalFormIntent<M>)

  abstract suspend fun deleteObject(state: MedicalFormViewState<M>, intent: MedicalFormIntent<M>)
}
