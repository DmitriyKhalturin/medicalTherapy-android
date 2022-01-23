package org.medicine.ui.screen.dealform

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.CoroutineScope
import org.medicine.ui.screen.dealform.composable.DealDeleteSuccessful
import org.medicine.ui.screen.dealform.composable.DealFrom
import org.medicine.ui.screen.dealform.composable.DealSaveSuccessful
import org.medicine.ui.screen.dealform.model.DealFormModel
import org.medicine.ui.screen.dealform.model.DealFormViewState

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for MedicalTherapy on 23.01.2022 18:11.
 */

@Composable
fun DealFormView(
  uiState: DealFormViewState,
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
      is DealFormViewState.Initial -> Unit
      is DealFormViewState.Deal -> uiState.run {
        DealFrom(
          dealId,
          deal,
          { dealFormOnChange(deal.copy(name = it)) },
          { dealFormOnChange(deal.copy(description = it)) },
          {  },
          {  },
          { createOrSaveDeal(therapyId, dealId, deal) },
          { dealId?.let(deleteDeal) },
        )
      }
      is DealFormViewState.SaveOnSuccessful -> uiState.run {
        DealSaveSuccessful()
      }
      is DealFormViewState.DeleteOnSuccessful -> uiState.run {
        DealDeleteSuccessful()
      }
    }
  }
}
