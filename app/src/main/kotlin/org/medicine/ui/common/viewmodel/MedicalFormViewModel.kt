package org.medicine.ui.common.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import org.medicine.common.exception.UnimplementedViewStateException
import org.medicine.navigation.Destination
import org.medicine.navigation.viewmodel.NavigationViewModel
import org.medicine.tools.viewmodel.IntentHandler
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
        is MedicalFormIntent.EnterScreen -> handleEnterScreen(state)
        is MedicalFormIntent.FillForm -> handleFillForm(state, intent)
        is MedicalFormIntent.CreateOrSaveObject -> handleCreateOrSaveObject(state, intent)
        is MedicalFormIntent.DeleteObject -> handleDeleteObject(state, intent)
      }
    }
  }

  protected suspend fun handleEnterScreen(state: MedicalFormViewState<M>) {
    fetchObject()
  }

  protected suspend fun handleFillForm(state: MedicalFormViewState<M>, intent: MedicalFormIntent.FillForm<M>) {
    when (state) {
      is MedicalFormViewState.Object -> fillFormModel(intent.`object`)
      else -> throw UnimplementedViewStateException(intent, state)
    }
  }

  protected suspend fun handleCreateOrSaveObject(state: MedicalFormViewState<M>, intent: MedicalFormIntent.CreateOrSaveObject<M>) {
    when (state) {
      is MedicalFormViewState.Object -> createOrSaveObject(intent.objectId, intent.`object`)
      else -> throw UnimplementedViewStateException(intent, state)
    }
  }

  protected suspend fun handleDeleteObject(state: MedicalFormViewState<M>, intent: MedicalFormIntent.DeleteObject<M>) {
    when (state) {
      is MedicalFormViewState.Object -> deleteObject(intent.objectId)
      else -> throw UnimplementedViewStateException(intent, state)
    }
  }

  protected abstract suspend fun fetchObject()
  protected abstract suspend fun fillFormModel(model: M)
  protected abstract suspend fun createOrSaveObject(id: Long?, model: M)
  protected abstract suspend fun deleteObject(id: Long)
}
