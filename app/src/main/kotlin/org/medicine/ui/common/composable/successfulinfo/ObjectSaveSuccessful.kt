package org.medicine.ui.common.composable.successfulinfo

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.CoroutineScope

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for MedicalTherapy on 19.01.2022 23:21.
 */

@Composable
fun ObjectSaveSuccessful(
  objectId: Long,
  callback: suspend CoroutineScope.(Long) -> Unit,
) {
  SuccessfulInfo(operation = SuccessfulOperation.Save)

  LaunchedEffect(objectId) {
    callback(objectId)
  }
}
