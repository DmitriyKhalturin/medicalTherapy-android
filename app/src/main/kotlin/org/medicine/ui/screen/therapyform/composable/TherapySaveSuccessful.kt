package org.medicine.ui.screen.therapyform.composable

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for MedicalTherapy on 19.01.2022 23:21.
 */

@Composable
fun TherapySaveSuccessful(
  therapyId: Long,
  callback: (Long) -> Unit,
) {
  LaunchedEffect(therapyId) {
    callback(therapyId)
  }
}