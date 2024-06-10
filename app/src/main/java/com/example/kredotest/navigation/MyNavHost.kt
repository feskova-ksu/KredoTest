package com.example.kredotest.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.navArgument
import com.example.kredotest.MainViewModel
import com.example.kredotest.ui.compose.CalendarScreen
import com.example.kredotest.ui.compose.MainScreen

@Composable
fun MyNavHost(navController: NavHostController, viewModel: MainViewModel) {
    NavHost(navController = navController, startDestination = "main") {
        composableWithAnimation("main") {
            MainScreen(viewModel, onNavigateToCalendar = {
                navController.navigate("calendar/${viewModel.uiState.value.selectedDate}")
            })
        }
        composableWithAnimation(
            "calendar/{date}",
            arguments = listOf(navArgument("date") { type = NavType.StringType })
        ) {
            CalendarScreen(onSave = viewModel::saveDate,
                selectedDate = it.arguments?.getString("date"),
                onBackClick = {
                    navController.navigateUp()
                })
        }
    }
}