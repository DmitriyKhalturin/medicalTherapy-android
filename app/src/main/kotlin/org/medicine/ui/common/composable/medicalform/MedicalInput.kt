package org.medicine.ui.common.composable.medicalform

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for MedicalTherapy on 23.01.2022 20:33.
 */

@Composable
internal fun MedicalInput(
  modifier: Modifier,
  label: String,
  isError: Boolean,
  value: String,
  onValueChange: (String) -> Unit,
) {
  val focusManager = LocalFocusManager.current
  val keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
  val keyboardActions = KeyboardActions(onDone = {
    focusManager.clearFocus()
  })

  OutlinedTextField(
    modifier = modifier,
    value = value,
    label = {
      Text(text = label)
    },
    isError = isError,
    onValueChange = {
      onValueChange(it)
    },
    keyboardOptions = keyboardOptions,
    keyboardActions = keyboardActions,
  )
}
