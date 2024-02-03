package com.example.firsttest

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation(
    viewModel: ProfileViewModel
){
    val navController = rememberNavController()
    //viewModel.insertUser(ProfileInfo( 0, Uri.parse("https://example.com/image.jpg"), "nimi"))

    NavHost(
        navController = navController,
        startDestination = Screen.MainScreen.route
    ){
        composable(
            route = Screen.MainScreen.route){
                MainScreen(navController, viewModel)
        }
        composable(
            route = Screen.SettingScreen.route){
                SettingScreen(navController, viewModel)
        }
    }
}
