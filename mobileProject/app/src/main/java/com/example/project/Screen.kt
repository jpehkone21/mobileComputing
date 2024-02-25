package com.example.project

sealed class Screen(val route: String) {
    data object MainScreen: Screen(route = "main_screen")
    data object CameraScreen: Screen(route = "camera_screen")
}