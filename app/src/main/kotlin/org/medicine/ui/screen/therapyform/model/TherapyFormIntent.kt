package org.medicine.ui.screen.therapyform.model

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for MedicalTherapy on 05.12.2021 21:27.
 */
sealed class TherapyFormIntent {
  object EnterScreen : TherapyFormIntent()
  data class FillTherapy(val therapy: TherapyFormModel) : TherapyFormIntent()
  data class CreateOrSaveTherapy(val therapyId: Long?, val therapy: TherapyFormModel) : TherapyFormIntent()
  data class DeleteTherapy(val therapyId: Long) : TherapyFormIntent()
}
