package org.medicine.ui.screen.dealform

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.CoroutineScope
import org.medicine.ui.common.composable.successfulinfo.ObjectDeleteSuccessful
import org.medicine.ui.common.composable.successfulinfo.ObjectSaveSuccessful
import org.medicine.ui.common.model.MedicalFormViewState
import org.medicine.ui.screen.dealform.composable.DealFrom
import org.medicine.ui.screen.dealform.model.DealFormModel

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for MedicalTherapy on 23.01.2022 18:11.
 */

@Composable
internal fun DealFormView(
  uiState: MedicalFormViewState<DealFormModel>,
  dealFormOnChange: (DealFormModel) -> Unit,
  createOrSaveDeal: (Long, Long?, DealFormModel) -> Unit,
  deleteDeal: (Long) -> Unit,
  saveOnSuccessful: suspend CoroutineScope.(Long) -> Unit,
  deleteOnSuccessful: suspend CoroutineScope.() -> Unit,
) {

  Box(
    modifier = Modifier.fillMaxSize(),
    contentAlignment = Alignment.TopCenter,
  ) {
    when (uiState) {
      is MedicalFormViewState.Initial -> Unit
      is MedicalFormViewState.Object -> uiState.run {
        DealFrom(
          objectId,
          `object`,
          { dealFormOnChange(`object`.copy(name = it)) },
          { dealFormOnChange(`object`.copy(description = it)) },
          {  },
          {  },
          { createOrSaveDeal(-1L, objectId, `object`) },
          { objectId?.let(deleteDeal) },
        )
      }
      is MedicalFormViewState.SaveOnSuccessful -> uiState.run {
        ObjectSaveSuccessful(-1L) {}
      }
      is MedicalFormViewState.DeleteOnSuccessful -> uiState.run {
        ObjectDeleteSuccessful(-1L) {}
      }
    }
  }
}
