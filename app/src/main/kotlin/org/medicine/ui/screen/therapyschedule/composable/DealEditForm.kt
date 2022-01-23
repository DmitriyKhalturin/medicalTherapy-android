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
  dealId: Long,
  therapyOnRefresh: () -> Unit,
) {
  val viewModel = hiltViewModel<DealFormViewModel>().apply {
    destination = Destination.DealForm(therapyId, dealId)
    obtainIntent(DealFormIntent.EnterScreen)
  }

  val uiState = viewModel.uiState

  DealFormView(
    uiState,
    { viewModel.obtainIntent(DealFormIntent.FillDeal(it)) },
    { _, _, dealForm -> viewModel.obtainIntent(DealFormIntent.CreateOrSaveDeal(therapyId, dealId, dealForm)) },
    { viewModel.obtainIntent(DealFormIntent.DeleteDeal(dealId)) },
    { therapyOnRefresh() },
    { therapyOnRefresh() },
  )
}
