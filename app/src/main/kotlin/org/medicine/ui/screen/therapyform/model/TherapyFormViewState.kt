package org.medicine.ui.screen.therapyform.model

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for MedicalTherapy on 05.12.2021 21:26.
 */
sealed class TherapyFormViewState {
  object Initial : TherapyFormViewState()
  data class Therapy(
    val therapyId: Long?,
    val therapy: TherapyFormModel,
  ) : TherapyFormViewState()
  object SavingSuccessful: TherapyFormViewState()
  object DeletingSuccessful: TherapyFormViewState()
}
