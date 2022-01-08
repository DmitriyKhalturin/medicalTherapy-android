package org.medicine.ui.screen.overview.bottomappbarstate

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import org.medicine.navigation.Destination
import org.medicine.navigation.bottomappbarstate.BottomAppBarState
import org.medicine.navigation.navigate
import org.medicine.tools.EMPTY_STRING

class OverviewBottomAppBarState(private val navController: NavController) : BottomAppBarState {
  @Composable override fun statusBarColor(): Color = MaterialTheme.colors.background
  @Composable override fun navigationBarColor(): Color = MaterialTheme.colors.primaryVariant
  override val floatingActionButtonPosition: FabPosition = FabPosition.Center

  @Composable
  override fun BuildFloatingActionButton() {
    FloatingActionButton(
      onClick = {
        navController.navigate(Destination.TherapyForm())
      },
      content = {
        Icon(Icons.Filled.Add, EMPTY_STRING, modifier = Modifier.scale(1.25f))
      }
    )
  }

  override val isVisibleBottomAppBar: Boolean = true

  @Composable
  override fun BuildBottomAppBarContent(context: RowScope) = with(context) {
    Spacer(modifier = Modifier.weight(weight = 1f, fill = true))
    IconButton(
      onClick = {
        navController.navigate(Destination.ApplicationInfo)
      },
      content = {
        Icon(Icons.Outlined.Info, EMPTY_STRING, tint = Color.White)
      }
    )
  }
}
