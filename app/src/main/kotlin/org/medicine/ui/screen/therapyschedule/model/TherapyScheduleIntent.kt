package org.medicine.ui.screen.therapyschedule.model

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for MedicalTherapy on 11.01.2022 19:48.
 */
sealed class TherapyScheduleIntent {
  object EnterScreen : TherapyScheduleIntent()
  object RefreshTherapy : TherapyScheduleIntent()
}
