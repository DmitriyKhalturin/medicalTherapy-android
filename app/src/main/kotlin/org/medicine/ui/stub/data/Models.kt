package org.medicine.ui.stub.data

import org.medicine.tools.EMPTY_STRING
import org.medicine.ui.screen.overview.model.TherapyModel
import org.medicine.ui.screen.therapyform.model.TherapyFormModel
import java.time.LocalDate

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
        description = "Composables should be side-effect free. However, when they're necessary to mutate the state of " +
          "the app, they should be called from a controlled environment that is aware of the lifecycle of the composable."
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
    name = "My first therapy.",
    EMPTY_STRING,
    todayDate,
    todayDate.plusDays(5),
    TherapyFormModel.FailedFields(description = true)
  )
}
