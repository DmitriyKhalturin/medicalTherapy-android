package org.medicine

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.BottomAppBar
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import org.medicine.navigation.bottomappbarstate.BottomAppBarState
import org.medicine.navigation.bottomappbarstate.DefaultBottomAppBarState
import org.medicine.navigation.buildNavGraph
import org.medicine.ui.theme.MedicalTherapyTheme

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for Medicine on 11.11.2021 0:03.
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContent {
      MedicalTherapyTheme {
        val scaffoldState = rememberScaffoldState()
        val navController = rememberNavController()

        val defaultBottomAppBarState = DefaultBottomAppBarState()
        val bottomAppBarState: MutableState<BottomAppBarState> = remember {
          mutableStateOf(defaultBottomAppBarState)
        }

        SetSystemUiState(bottomAppBarState.value)

        val navGraph = buildNavGraph(navController) {
          SetSystemUiState(it)

          bottomAppBarState.value = it
        }

        Scaffold(
          scaffoldState = scaffoldState,
          floatingActionButtonPosition = bottomAppBarState.value.floatingActionButtonPosition,
          floatingActionButton = {
            bottomAppBarState.value.BuildFloatingActionButton()
          },
          isFloatingActionButtonDocked = true,
          bottomBar = {
            if (bottomAppBarState.value.isVisibleBottomAppBar) {
              BottomAppBar(cutoutShape = MaterialTheme.shapes.medium.copy(CornerSize(percent = 50))) {
                bottomAppBarState.value.BuildBottomAppBarContent(this)
              }
            }
          },
        ) { innerPadding ->
          Box(
            modifier = Modifier
              .fillMaxSize()
              .padding(innerPadding)
          ) {
            NavHost(
              navController = navController,
              graph = navGraph,
            )
          }
        }
      }
    }
  }

  @Composable
  private fun SetSystemUiState(state: BottomAppBarState) {
    val systemUiController = rememberSystemUiController()

    systemUiController.setStatusBarColor(color = state.statusBarColor())
    systemUiController.setNavigationBarColor(color = state.navigationBarColor())
  }
}
