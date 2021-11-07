package org.medicine.ui.theme

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

val Shapes = Shapes(
  small = RoundedCornerShape(4.dp),
  medium = RoundedCornerShape(4.dp),
  large = RoundedCornerShape(0.dp)
)

@Preview(showBackground = true)
@Composable
fun SmallShapePreview() {
  MedicineTheme {
    MaterialTheme.colors.apply {
      Sample(primary, onPrimary, Shapes.small)
    }
  }
}

@Preview(showBackground = true)
@Composable
fun MediumShapePreview() {
  MedicineTheme {
    MaterialTheme.colors.apply {
      Sample(secondary, onSecondary, Shapes.medium)
    }
  }
}

@Preview(showBackground = true)
@Composable
fun LargeShapePreview() {
  MedicineTheme {
    MaterialTheme.colors.apply {
      Sample(surface, onSurface, Shapes.large)
    }
  }
}

@Composable
fun Sample(surfaceColor: Color, contentColor: Color, shape: Shape) {
  Surface(
    color = MaterialTheme.colors.surface,
    modifier = Modifier.padding(16.dp),
  ) {
    Surface(
      color = surfaceColor,
      shape = shape,
      contentColor = contentColor,
    ) {
      Text(
        text = "Sample",
        modifier = Modifier.padding(8.dp),
      )
    }
  }
}
