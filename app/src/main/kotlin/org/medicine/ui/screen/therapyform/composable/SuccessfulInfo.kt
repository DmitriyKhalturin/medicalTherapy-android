package org.medicine.ui.screen.therapyform.composable

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircleOutline
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.medicine.tools.EMPTY_STRING
import org.medicine.ui.theme.MedicalTherapyTheme

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for MedicalTherapy on 21.01.2022 23:36.
 */

@Composable
internal fun SuccessfulInfo(operation: SuccessfulOperation) {
  Box(
    modifier = Modifier.fillMaxSize(),
    contentAlignment = Alignment.Center,
  ) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
      Icon(
        Icons.Outlined.CheckCircleOutline,
        EMPTY_STRING,
        modifier = Modifier.size(48.dp),
        tint = MaterialTheme.colors.primaryVariant,
      )

      val modifier = Modifier.padding(16.dp)

      when (operation) {
        SuccessfulOperation.Save -> Text(text = "Успешно сохранено", modifier = modifier)
        SuccessfulOperation.Delete -> Text(text = "Успешно удалено", modifier = modifier)
      }
    }
  }
}

internal enum class SuccessfulOperation {
  Save,
  Delete;
}


@Preview(showBackground = true)
@Composable
fun TherapySaveSuccessfulPreview() {
  MedicalTherapyTheme {
    SuccessfulInfo(SuccessfulOperation.Save)
  }
}
