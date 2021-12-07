package org.medicine.ui.screen.overview.composable.therapies.item

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.medicine.ui.screen.overview.model.TherapyModel
import org.medicine.ui.stub.data.stubTherapy
import org.medicine.ui.theme.MedicalTherapyTheme
import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for MedicalTherapy on 16.11.2021 10:09.
 */

@Composable
fun ActiveTherapyItem(
  therapy: TherapyModel,
  openTherapyOnClick: (Long) -> Unit
) {
  Surface(
    modifier = Modifier
      .fillMaxWidth()
      .clickable { openTherapyOnClick(therapy.id) }
      .padding(8.dp),
    shape = MaterialTheme.shapes.small,
    elevation = 12.dp,
  ) {
    Column(modifier = Modifier.padding(8.dp)) {
      Text(
        text = therapy.name,
        fontSize = 17.sp,
        fontWeight = FontWeight.Bold,
      )

      therapy.description?.let { description ->
        Text(
          text = description,
          modifier = Modifier.padding(top = 4.dp, start = 10.dp, end = 8.dp),
          maxLines = 3,
          overflow = TextOverflow.Ellipsis,
          fontSize = 15.sp,
        )
      }

      val pattern = DateTimeFormatter.ofPattern("dd MMMM")

      val date = if (LocalDate.now().isBefore(therapy.startDate)) {
        "Лечение начнется ${therapy.startDate.format(pattern)}"
      } else {
        "Лечение закончиться ${therapy.endDate.format(pattern)}"
      }

      Text(
        text = date,
        modifier = Modifier.padding(top = 8.dp),
        fontSize = 13.sp,
        fontStyle = FontStyle.Italic,
      )
    }
  }
}


@Preview(showBackground = true)
@Composable
fun ActiveTherapyItemPreview() {
  MedicalTherapyTheme {
    ActiveTherapyItem(therapy = stubTherapy(), openTherapyOnClick = { })
  }
}
