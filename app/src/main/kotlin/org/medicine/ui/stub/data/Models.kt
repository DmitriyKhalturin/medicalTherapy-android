package org.medicine.ui.stub.data

import org.medicine.ui.screen.overview.model.TherapyModel
import java.time.LocalDate

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for MedicalTherapy on 16.11.2021 15:50.
 */
object Models {

  private val startDate = LocalDate.now()
  private val endDate = startDate.plusDays(4)

  val therapy = TherapyModel(
    id = 0L,
    name = "Протокол лечение при заболевании.",
    description = null,
    startDate = startDate,
    endDate = endDate,
  )
}
