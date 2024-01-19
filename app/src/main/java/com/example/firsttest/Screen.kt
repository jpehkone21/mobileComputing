package com.example.firsttest

sealed class Screen(val route: String) {
    data object MainScreen: Screen(route = "main_screen")
    data object SettingScreen: Screen(route = "setting_screen")
}