package org.medicine.ui.theme

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for MedicalTherapy on 15.11.2021 7:59.
 */

@Stable
data class LeafShapes(
  val ltr: CornerBasedShape = RoundedCornerShape(
    topStart = 1.dp,
    bottomEnd = 1.dp,
    topEnd = 16.dp,
    bottomStart = 16.dp,
  ),
  val rtl: CornerBasedShape = RoundedCornerShape(
    topStart = 16.dp,
    bottomEnd = 16.dp,
    topEnd = 1.dp,
    bottomStart = 1.dp,
  ),
)

val LocalLeafShapes = staticCompositionLocalOf { LeafShapes() }


@Preview(showBackground = true)
@Composable
fun SmallShapePreview() {
  MedicalTherapyTheme {
    val leafShape = LeafShapes()
    Sample("leftToRight", leafShape.ltr, 12.dp)
  }
}

@Preview(showBackground = true)
@Composable
fun MediumShapePreview() {
  MedicalTherapyTheme {
    val leafShape = LeafShapes()
    Sample("rightToLeft", leafShape.rtl, 12.dp)
  }
}

@Composable
fun Sample(sampleIdentifier: String, shape: Shape, allPadding: Dp) {
  Surface(
    modifier = Modifier.padding(24.dp),
    shape = shape,
    color = MaterialTheme.colors.primary,
    contentColor = MaterialTheme.colors.onPrimary,
    elevation = 2.dp,
  ) {
    Text(
      text = sampleIdentifier,
      modifier = Modifier.padding(allPadding),
    )
  }
}
