package org.medicine.ui.common.composable.medicalform

import androidx.compose.foundation.clickable
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for MedicalTherapy on 23.01.2022 20:33.
 */

@Composable
internal fun MedicalClickableInput(
  modifier: Modifier,
  label: String,
  isError: Boolean,
  value: String,
  onClick: () -> Unit,
) {
  val focusManager = LocalFocusManager.current

  OutlinedTextField(
    modifier = modifier
      .clickable {
        focusManager.clearFocus()
        onClick()
      },
    value = value,
    label = { Text(label) },
    isError = isError,
    onValueChange = {},
    enabled = false,
  )
}
