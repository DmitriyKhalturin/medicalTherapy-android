package org.medicine.ui.screen.dayschedule.model

import org.medicine.source.database.entity.MedicineType
import java.time.LocalDate
import java.time.LocalTime

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for MedicalTherapy on 30.01.2022 21:52.
 */
data class DayScheduleModel(
  val name: String,
  val date: LocalDate,
  val medicines: List<Medicine>,
  val deals: List<Deal>,
) {

  data class Medicine(
    val id: Long,
    val name: String,
    val description: String,
    val type: MedicineType,
    val dosage: String,
    val times: List<LocalTime>,
  )

  data class Deal(
    val id: Long,
    val name: String,
    val description: String,
    val time: LocalTime,
  )
}
