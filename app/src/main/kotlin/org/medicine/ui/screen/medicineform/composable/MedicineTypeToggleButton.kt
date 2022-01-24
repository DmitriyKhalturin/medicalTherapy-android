package org.medicine.ui.screen.medicineform.composable

import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import org.medicine.schedule.data.Medicine
import org.medicine.tools.EMPTY_STRING

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for MedicalTherapy on 25.01.2022 1:03.
 */

@Composable
fun MedicineTypeToggleButton(
  value: Medicine.Type,
  checked: Medicine.Type,
  onCheckedChange: (Medicine.Type) -> Unit,
) {
  IconToggleButton(
    checked = value == checked,
    onCheckedChange = {
      if (it) onCheckedChange(checked)
    },
  ) {
    Icon(painter = painterResource(id = checked.iconResId), EMPTY_STRING)
  }
}
