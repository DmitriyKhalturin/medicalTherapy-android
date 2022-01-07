package org.medicine.ui.screen.overview

import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import org.medicine.navigation.bottomappbarstate.BottomAppBarState
import org.medicine.tools.EMPTY_STRING

class OverviewBottomAppBarState : BottomAppBarState {
  @Composable override fun statusBarColor(): Color = MaterialTheme.colors.background
  @Composable override fun navigationBarColor(): Color = MaterialTheme.colors.primaryVariant
  override val floatingActionButtonPosition: FabPosition = FabPosition.Center

  @Composable
  override fun BuildFloatingActionButton() {
    FloatingActionButton(
      onClick = {
                //
      },
      content = {
        Icon(Icons.Filled.Add, EMPTY_STRING)
      }
    )
  }

  @Composable
  override fun BuildBottomAppBar() {
    BottomAppBar(cutoutShape = MaterialTheme.shapes.medium.copy(CornerSize(percent = 50))) {
      //
    }
  }
}
