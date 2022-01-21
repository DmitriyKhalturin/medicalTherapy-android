package org.medicine.ui.screen.dealform

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.medicine.ui.screen.dealform.composable.DealFrom
import org.medicine.ui.screen.dealform.model.DealFormViewState

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for Medicine on 10.11.2021 23:57.
 */

@Composable
fun DealFormScreen(viewModel: DealFormViewModel) {
}

@Composable
fun DealFormView(
  uiState: DealFormViewState,
) {

  Box(
    modifier = Modifier.fillMaxSize(),
    contentAlignment = Alignment.TopCenter,
  ) {
    when (uiState) {
      is DealFormViewState.Initial -> Unit
      is DealFormViewState.Deal -> uiState.run {
        DealFrom(

        )
      }
    }
  }
}
