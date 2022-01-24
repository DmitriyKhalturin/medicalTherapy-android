package org.medicine.ui.common.composable.medicalform

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.medicine.tools.EMPTY_STRING

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for MedicalTherapy on 21.01.2022 12:32.
 */

@Composable
internal fun SkelMedicalForm(
  objectId: Long?,
  createOrSaveObject: () -> Unit,
  deleteObject: () -> Unit,
  fieldsCallback: @Composable ColumnScope.(Modifier) -> Unit,
) {
  Column(
    modifier = Modifier.padding(16.dp),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Column(modifier = Modifier.weight(weight = 1f)) {
      val fieldModifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 4.dp)

      fieldsCallback(fieldModifier)
    }

    Column {
      val controlModifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 16.dp)

      OutlinedButton(
        modifier = controlModifier,
        onClick = {
          createOrSaveObject()
        }
      ) {
        if (objectId != null) {
          Text(text = "Сохранить")
        } else {
          Text(text = "Создать")
        }
      }

      if (objectId != null) {
        Button(
          modifier = controlModifier,
          onClick = {
            deleteObject()
          }
        ) {
          Icon(Icons.Filled.Delete, EMPTY_STRING)
          Text(text = "Удалить ")
        }
      }
    }
  }
}
