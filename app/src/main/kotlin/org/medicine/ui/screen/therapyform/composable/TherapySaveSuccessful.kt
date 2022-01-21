package org.medicine.ui.screen.therapyform.composable

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.CoroutineScope

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for MedicalTherapy on 19.01.2022 23:21.
 */

@Composable
fun TherapySaveSuccessful(
  therapyId: Long,
  callback: suspend CoroutineScope.(Long) -> Unit,
) {
  SuccessfulInfo(operation = SuccessfulOperation.Save)

  LaunchedEffect(therapyId) {
    callback(therapyId)
  }
}
