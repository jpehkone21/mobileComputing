package com.example.project

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation() {
    val viewModel = viewModel<MainViewModel>()

    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.MainScreen.route
    ) {
        composable(
            route = Screen.MainScreen.route
        ) {
            MainScreen(navController, viewModel)
        }
        composable(
            route = Screen.CameraScreen.route
        ) {
            CameraScreen(navController, viewModel)
        }
    }
}