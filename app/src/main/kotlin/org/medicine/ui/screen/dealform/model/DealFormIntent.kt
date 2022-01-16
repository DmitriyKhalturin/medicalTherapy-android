package org.medicine.ui.screen.dealform.model

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for MedicalTherapy on 16.01.2022 18:58.
 */
sealed class DealFormIntent {
  object EnterScreen : DealFormIntent()
}
