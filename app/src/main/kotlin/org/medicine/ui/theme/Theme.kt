package org.medicine.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import org.medicine.R

private val LightColorPalette
  @Composable get() = lightColors(
    primary = colorResource(id = R.color.mediumRose),
    primaryVariant = colorResource(id = R.color.rose),
    onPrimary = colorResource(id = R.color.haleNavy),

    secondary = colorResource(id = R.color.greenishBlue),
    secondaryVariant = colorResource(id = R.color.grayTurquoise),
    onSecondary = colorResource(id = R.color.seashell),

    background = colorResource(id = R.color.background),
    onBackground = colorResource(id = R.color.haleNavy),

    surface = colorResource(id = R.color.lightGray),
    onSurface = colorResource(id = R.color.darkGray),
  )

@Composable
fun MedicineTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
  val colors = LightColorPalette

  MaterialTheme(
    colors = colors,
    typography = Typography,
    shapes = Shapes,
    content = content
  )
}
