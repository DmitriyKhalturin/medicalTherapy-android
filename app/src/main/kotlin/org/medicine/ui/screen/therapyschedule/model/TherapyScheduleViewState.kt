package org.medicine.ui.screen.therapyschedule.model

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for MedicalTherapy on 11.01.2022 19:50.
 */
sealed class TherapyScheduleViewState {
  object Initial : TherapyScheduleViewState()
  data class TherapySchedule(val therapyId: Long, val therapy: TherapyScheduleModel) : TherapyScheduleViewState()
}
