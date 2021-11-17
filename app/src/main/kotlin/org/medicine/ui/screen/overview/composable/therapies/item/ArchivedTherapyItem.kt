package org.medicine.ui.screen.overview.composable.therapies.item

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Archive
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.medicine.tools.EMPTY_STRING
import org.medicine.ui.screen.overview.model.TherapyModel
import org.medicine.ui.stub.data.stubTherapy
import org.medicine.ui.theme.MedicalTherapyTheme
import java.time.format.DateTimeFormatter

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for MedicalTherapy on 16.11.2021 10:11.
 */

@Composable
fun ArchivedTherapyItem(
  therapy: TherapyModel,
  openTherapyOnClick: (Long) -> Unit,
) {
  Surface(
    modifier = Modifier
      .fillMaxWidth()
      .clickable { openTherapyOnClick(therapy.id) }
      .padding(8.dp),
    shape = MaterialTheme.shapes.small,
    elevation = 12.dp,
  ) {
    Row(modifier = Modifier.padding(8.dp)) {
      Icon(
        Icons.Filled.Archive,
        EMPTY_STRING,
        modifier = Modifier.padding(top = 2.dp, start = 2.dp)
      )
      Spacer(modifier = Modifier.width(8.dp))
      Column {
        Text(
          text = therapy.name,
          fontSize = 17.sp,
          fontWeight = FontWeight.Bold,
        )

        val pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd")

        Text(
          text = "Применялся с ${therapy.startDate.format(pattern)} по ${therapy.endDate.format(pattern)}.",
          modifier = Modifier.padding(top = 8.dp),
          fontSize = 13.sp,
          fontStyle = FontStyle.Italic,
        )
      }
    }
  }
}


@Preview(showBackground = true)
@Composable
fun ArchivedTherapyItemPreview() {
  MedicalTherapyTheme {
    ArchivedTherapyItem(therapy = stubTherapy(), openTherapyOnClick = { })
  }
}
