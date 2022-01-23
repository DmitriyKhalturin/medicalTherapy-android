package org.medicine.ui.screen.dealform.model

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for MedicalTherapy on 16.01.2022 18:58.
 */
sealed class DealFormIntent {
  object EnterScreen : DealFormIntent()
  data class FillDeal(val deal: DealFormModel) : DealFormIntent()
  data class CreateOrSaveDeal(val therapyId: Long, val dealId: Long?, val deal: DealFormModel) : DealFormIntent()
  data class DeleteDeal(val dealId: Long) : DealFormIntent()
}
