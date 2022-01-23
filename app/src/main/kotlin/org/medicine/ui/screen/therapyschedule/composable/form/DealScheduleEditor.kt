package org.medicine.ui.screen.therapyschedule.composable.form

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import org.medicine.navigation.Destination
import org.medicine.ui.common.model.MedicalFormIntent
import org.medicine.ui.screen.dealform.DealFormView
import org.medicine.ui.screen.dealform.DealFormViewModel

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for MedicalTherapy on 15.01.2022 11:06.
 */

@Composable
fun DealScheduleEditor(
  therapyId: Long,
  dealId: Long,
  therapyOnRefresh: () -> Unit,
) {
  val viewModel = hiltViewModel<DealFormViewModel>().apply {
    destination = Destination.DealForm(therapyId, dealId)
    obtainIntent(MedicalFormIntent.EnterScreen())
  }

  val uiState = viewModel.uiState

  DealFormView(
    uiState,
    { viewModel.obtainIntent(MedicalFormIntent.FillForm(it)) },
    { _, _, dealForm -> viewModel.obtainIntent(MedicalFormIntent.CreateOrSaveObject(dealId, dealForm)) },
    { viewModel.obtainIntent(MedicalFormIntent.DeleteObject(dealId)) },
    { therapyOnRefresh() },
    { therapyOnRefresh() },
  )
}
