package com.example.firsttest

import android.Manifest
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.room.Room
import coil.compose.AsyncImage
import java.util.UUID


@Composable
fun SettingScreen(
    navController: NavController,
    viewModel: ProfileViewModel
){

    val userInfo = viewModel.getInfo(1)

    var username = (userInfo.observeAsState().value?.get(0)?.username.toString() )
    //var usernameTextField by remember { mutableStateOf(TextFieldValue(username))}
    var profilePhoto = (userInfo.observeAsState().value?.get(0)?.photoUri.toString())

    var filename = "profilePic.jpg"



    var selectedImageUri by remember{
        mutableStateOf<String>(profilePhoto)
    }
    var selectedImageUri2 by remember{
        mutableStateOf<Uri?>(null)
    }
    var imageFilePath by remember {
        mutableStateOf<String>(profilePhoto)
    }

    val context = LocalContext.current
    val pickMedia = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { newUri: Uri? ->
            selectedImageUri = newUri.toString()
            selectedImageUri2 = newUri


            selectedImageUri2?.let {
                if (newUri != null) {
                    if(selectedImageUri != profilePhoto){
                        filename = UUID.randomUUID().toString()
                    }


                        val fileContents = selectedImageUri2
                        val inputstream = context.contentResolver.openInputStream(newUri)
                        val outputFile = context.filesDir.resolve(filename)
                        inputstream?.copyTo(outputFile.outputStream())
                        imageFilePath = outputFile.toString()
                        viewModel.addInfo(ProfileInfo( 1, imageFilePath, username))



                    //}
                }
            }
        }
    )

    var hasNotificationPermission by remember{
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            mutableStateOf(
                ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED
            )
        } else {
            mutableStateOf(true)
        }
    }
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = {isGranted ->
            hasNotificationPermission = isGranted

        }
    )

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
            text = "Change profile picture and username here." ,
            color = MaterialTheme.colorScheme.secondary,
            style = MaterialTheme.typography.bodyMedium
        )
            AsyncImage(
                model = profilePhoto,
                //placeholder = painterResource(R.drawable.kissa),
                contentDescription = "profile picture",
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .border(1.5.dp, MaterialTheme.colorScheme.primary, CircleShape)
                    .clickable {
                        pickMedia.launch(
                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                        )

                        //profilePhoto = selectedImageUri
                    }
            )
        //}



        OutlinedTextField(
            value = username,
            onValueChange = {
                //username = it
                viewModel.addInfo(ProfileInfo( 1, imageFilePath, it))
                            //username = newUsername.toString()
            },
            label = { Text("Username") }
        )/*
        Button(
            onClick = {
                //if (viewModel.getItemCount() <= 0){
                 //   viewModel.insertUser(ProfileInfo(0, selectedImageUri.toString(), newUsername))
               // }else {
                    //if (newUsername.isNotBlank()) {
                        viewModel.addInfo(ProfileInfo( 1, imageFilePath, username))
                        //usernameTextField = username
                        //profilePhoto = selectedImageUri

            //viewModel.updateUsername(newUsername)


                    //}
                //}
                //viewModel.updatePhoto(selectedImageUri.toString())
            }
        ) {
            Text("save changes")
        }*/
        if (!hasNotificationPermission){
            Button(
                onClick = {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        permissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                    }
                }
            ) {
                Text("Allow notifications")
            }
        }else{
            Text(
                text = "Notifications allowed." ,
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.bodyMedium
            )
        }

    }


}
