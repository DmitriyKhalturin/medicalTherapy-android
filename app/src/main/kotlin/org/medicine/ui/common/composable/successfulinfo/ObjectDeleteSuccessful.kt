package org.medicine.ui.common.composable.successfulinfo

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.CoroutineScope

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for MedicalTherapy on 19.01.2022 23:21.
 */

@Composable
fun ObjectDeleteSuccessful(
  objectId: Long,
  callback: suspend CoroutineScope.() -> Unit,
) {
  SuccessfulInfo(operation = SuccessfulOperation.Delete)

  LaunchedEffect(objectId) {
    callback()
  }
}
