package org.medicine.ui.screen.therapyschedule.composable

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import org.medicine.navigation.Destination
import org.medicine.ui.screen.dealform.DealFormView
import org.medicine.ui.screen.dealform.DealFormViewModel
import org.medicine.ui.screen.dealform.model.DealFormIntent

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for MedicalTherapy on 15.01.2022 11:06.
 */

@Composable
fun DealEditForm(
  therapyId: Long,
  refreshTherapyCallback: () -> Unit,
) {
  val viewModel = hiltViewModel<DealFormViewModel>().apply {
    destination = Destination.DealForm(therapyId)
    obtainIntent(DealFormIntent.EnterScreen)
  }

  val uiState = viewModel.uiState

  DealFormView(
    uiState,
  )
}
