package com.example.firsttest

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import com.example.firsttest.ui.theme.FirstTestTheme
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room


class MainActivity : ComponentActivity() {

    private val viewModel: ProfileViewModel by viewModels()
/*
    private val db by lazy{
        Room.databaseBuilder(
        applicationContext,
        ProfileInfoDatabase::class.java, "profileInfo.dp"
    ).build()
    }

    private val viewModel by viewModels<ProfileViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return ProfileViewModel(db.dao) as T
                }
            }
        }
    )
    */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FirstTestTheme {
                //viewModel.insertUser(ProfileInfo(0, , "jokunimi"))

                Navigation(viewModel)
            }

        }
    }
}



