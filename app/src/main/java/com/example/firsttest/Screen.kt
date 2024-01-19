package com.example.firsttest

sealed class Screen(val route: String) {
    object MainScreen: Screen(route = "main_screen")
    object SettingScreen: Screen(route = "setting_screen")
}