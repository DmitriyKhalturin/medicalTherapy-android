package org.medicine.ui.screen.dayschedule.model

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for MedicalTherapy on 30.01.2022 3:33.
 */
sealed class DayScheduleIntent {
  object EnterScreen : DayScheduleIntent()
}
