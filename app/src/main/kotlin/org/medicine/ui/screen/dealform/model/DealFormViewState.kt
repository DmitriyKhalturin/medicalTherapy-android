package org.medicine.ui.screen.dealform.model

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for MedicalTherapy on 16.01.2022 18:59.
 */
sealed class DealFormViewState {
  object Initial : DealFormViewState()
  data class Deal(val therapyId: Long, val dealId: Long?, val deal: DealFormModel) : DealFormViewState()
  object SaveOnSuccessful : DealFormViewState()
  object DeleteOnSuccessful : DealFormViewState()
}
