package com.example.kredotest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.kredotest.tools.animation.ScaleTransitionDirection
import com.example.kredotest.tools.animation.scaleIntoContainer
import com.example.kredotest.tools.animation.scaleOutOfContainer
import com.example.kredotest.ui.compose.CalendarScreen
import com.example.kredotest.ui.compose.MainScreen
import com.example.kredotest.ui.theme.BackgroundBlue
import com.example.kredotest.ui.theme.KredoTestTheme


class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel = MainViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KredoTestTheme {
                val navController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = BackgroundBlue
                ) {
                    NavHost(navController = navController, startDestination = "main") {
                        composableWithAnimation("main") {
                            MainScreen(viewModel, onNavigateToCalendar = {
                                navController.navigate("calendar")
                            })
                        }
                        composableWithAnimation("calendar") {
                            CalendarScreen(viewModel = viewModel, onBackClick = {
                                navController.navigateUp()
                            })
                        }
                    }
                }
            }
        }
    }
}

fun NavGraphBuilder.composableWithAnimation(
    route: String,
    content: @Composable() (AnimatedContentScope.(NavBackStackEntry) -> Unit)
) {
    this.composable(
        route, enterTransition = {
            scaleIntoContainer()
        },
        exitTransition = {
            scaleOutOfContainer(direction = ScaleTransitionDirection.INWARDS)
        },
        popEnterTransition = {
            scaleIntoContainer(direction = ScaleTransitionDirection.OUTWARDS)
        },
        popExitTransition = {
            scaleOutOfContainer()
        }, content = content
    )
}

