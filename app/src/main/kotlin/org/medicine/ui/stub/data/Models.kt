package org.medicine.ui.stub.data

import org.medicine.schedule.data.Medicine
import org.medicine.source.database.entity.MedicineType
import org.medicine.tools.EMPTY_STRING
import org.medicine.ui.screen.dayschedule.model.DayScheduleModel
import org.medicine.ui.screen.dealform.model.DealFormModel
import org.medicine.ui.screen.medicineform.model.MedicineFormModel
import org.medicine.ui.screen.overview.model.TherapyModel
import org.medicine.ui.screen.therapyform.model.TherapyFormModel
import java.time.LocalDate
import java.time.LocalTime

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for MedicalTherapy on 16.11.2021 15:50.
 */

fun stubTherapy(): TherapyModel {
  val startDate = LocalDate.now()
  val endDate = startDate.plusDays(4)

  return TherapyModel(
    id = 0L,
    name = "Протокол лечение при заболевании.",
    description = null,
    startDate = startDate,
    endDate = endDate,
  )
}

fun stubActiveTherapies(): List<TherapyModel> {
  val therapy = stubTherapy()

  return List(3) { therapy }
    .mapIndexed { index, item ->
      if (index == 0) item.copy(
        description = "Подводя итог, хотелось бы отметить, что GraphQL — это концепция создания API, которая " +
          "обеспечивает слабую связность клиента и сервера. Очевидно, что с появлением этой технологии совершенно " +
          "не обязательно полностью отказываться от использования REST-архитектуры."
      ) else item
    }
}

fun stubArchivedTherapies(): List<TherapyModel> {
  val therapy = stubTherapy()

  return List(3) { therapy }
}

fun stubTherapyForm(): TherapyFormModel {
  val todayDate = LocalDate.now()

  return TherapyFormModel(
    name = "Первый протокол лечения.",
    EMPTY_STRING,
    todayDate,
    todayDate.plusDays(5),
    TherapyFormModel.FailedFields(description = true, date = true),
  )
}

fun stubDealForm(): DealFormModel {
  val today = LocalDate.now()

  return DealFormModel(
    therapyId = -1L,
    name = "Встреча с врачом.",
    EMPTY_STRING,
    today,
    LocalTime.now(),
    today,
    today,
    DealFormModel.FailedFields(description = true)
  )
}

fun stubMedicineForm(): MedicineFormModel {
  val today = LocalDate.now()
  val afterThreeDays = today.plusDays(3)
  val now = LocalTime.now()

  return MedicineFormModel(
    therapyId = -1L,
    name = "Лекарство",
    EMPTY_STRING,
    Medicine.Type.PILLS,
    EMPTY_STRING,
    today,
    afterThreeDays,
    listOf(now, now.plusHours(3), now.minusHours(2)),
    today,
    afterThreeDays,
    MedicineFormModel.FailedFields(description = true)
  )
}

fun stubDaySchedule(): DayScheduleModel {


  return DayScheduleModel(
    name = "Текущий день",
    LocalDate.now(),
    listOf(
      DayScheduleModel.Medicine(
        id = -1L,
        name = "Аспирин",
        description = "Принимать после еды",
        type = MedicineType.PILLS,
        dosage = "1 шт.",
        times = listOf(
          LocalTime.now()
        ),
      ),
      DayScheduleModel.Medicine(
        id = -1L,
        name = "Спрей",
        description = EMPTY_STRING,
        type = MedicineType.PILLS,
        dosage = EMPTY_STRING,
        times = listOf(
          LocalTime.now()
        ),
      )
    ),
    listOf(
      DayScheduleModel.Deal(
        id = -1L,
        name = "Встреча",
        description = "На Майне",
        time = LocalTime.now(),
      )
    ),
  )
}
