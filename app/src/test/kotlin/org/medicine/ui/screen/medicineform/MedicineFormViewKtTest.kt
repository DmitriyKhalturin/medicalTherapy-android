package org.medicine.ui.screen.medicineform

import org.junit.Test
import java.time.LocalTime

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86></dmitry.halturin.86>@gmail.com>
 * for MedicalTherapy on 24.01.2022 4:31.
 */
class MedicineFormViewKtTest {

    @Test
    fun `Test exclude time from list`() {
      val now = LocalTime.now()
      val standardList = listOf<LocalTime>(
        now,
        now.plusMinutes(1L),
        now.plusMinutes(10L),
        now,
      )
      val handledList = standardList.exclude(now)

      assert(handledList.size == standardList.size - 1)
    }
}
