package org.medicine.ui.screen.therapyschedule.composable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.outlined.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.medicine.tools.EMPTY_STRING
import org.medicine.ui.theme.MedicalTherapyTheme

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for MedicalTherapy on 14.01.2022 17:18.
 */

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun ToolButton(
  modifier: Modifier = Modifier,
  imageVector: ImageVector? = null,
  painter: Painter? = null,
  text: String,
  onClick: () -> Unit,
) {
  Surface(
    onClick = {
      onClick()
    },
    modifier = modifier.width(64.dp),
    shape = RoundedCornerShape(8.dp),
  ) {
    Column(
      modifier = Modifier.padding(8.dp),
      horizontalAlignment = Alignment.CenterHorizontally,
    ) {
      val iconModifier = Modifier.height(48.dp)

      when {
        imageVector != null -> Icon(
          imageVector,
          EMPTY_STRING,
          modifier = iconModifier,
          tint = MaterialTheme.colors.secondary
        )
        painter != null -> Icon(
          painter,
          EMPTY_STRING,
          modifier = iconModifier,
          tint = MaterialTheme.colors.secondary
        )
      }

      Text(
        text = text,
        fontSize = 8.sp,
        softWrap = false,
        overflow = TextOverflow.Ellipsis,
      )
    }
  }
}


@Preview(showBackground = true)
@Composable
fun ToolButtonPreview() {
  MedicalTherapyTheme {
    val modifier = Modifier.padding(horizontal = 4.dp)

    Row {
      ToolButton(modifier = modifier, text = "Редактировать", imageVector = Icons.Filled.Edit) {}
      ToolButton(modifier = modifier, text = "Информация", imageVector = Icons.Outlined.Info) {}
    }
  }
}
