package org.medicine.ui.stub.data

import org.medicine.tools.EMPTY_STRING
import org.medicine.ui.screen.dealform.model.DealFormModel
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
    TherapyFormModel.FailedFields(description = true),
  )
}

fun stubDealForm(): DealFormModel {

  return DealFormModel(
    name = "Встреча с врачом.",
    EMPTY_STRING,
    LocalDate.now(),
    LocalTime.now(),
    DealFormModel.FailedFields(description = true)
  )
}
