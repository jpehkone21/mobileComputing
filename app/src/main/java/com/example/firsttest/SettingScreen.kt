package com.example.firsttest

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.navigation.NavController
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SettingScreen(
    navController: NavController
){
    Column() {


        Row(modifier = Modifier.padding(all = 8.dp)) {

            Button(onClick = {
                navController.navigate(route = Screen.MainScreen.route){
                    popUpTo(Screen.MainScreen.route){
                        inclusive = true
                    }
                }

            }) {
                Text("Back")
            }


            Text(
                text = "Settings",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.headlineMedium
            )
        }

        Text(
            text = "Some settings here.",
            color = MaterialTheme.colorScheme.secondary,
            style = MaterialTheme.typography.bodyMedium
        )
    }


}