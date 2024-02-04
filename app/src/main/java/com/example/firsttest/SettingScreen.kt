package com.example.firsttest

import android.net.Uri
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
import androidx.core.net.toUri
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.room.Room
import coil.compose.AsyncImage





@Composable
fun SettingScreen(
    navController: NavController,
    viewModel: ProfileViewModel
){
    //val username: String = viewModel.getUsername()
    //val profilePhoto: String = viewModel.getPhoto()

    var username by remember { mutableStateOf("default") }
    var profilePhoto: String by remember { mutableStateOf("") }
    val userInfo = viewModel.getInfo(1)

    username = (userInfo.observeAsState().value?.get(0)?.username.toString() )
    //var usernameTextField by remember { mutableStateOf(TextFieldValue(username))}
    profilePhoto = (userInfo.observeAsState().value?.get(0)?.photoUri.toString())

    //var newUsername by remember { mutableStateOf(username) }

    var selectedImageUri by remember{
        mutableStateOf<String>(profilePhoto)
    }
    var selectedImageUri2 by remember{
        mutableStateOf<Uri?>(null)
    }

    val context = LocalContext.current
    val pickMedia = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            selectedImageUri = uri.toString()

            //profilePhoto = selectedImageUri
            viewModel.addInfo(ProfileInfo( 1, selectedImageUri, username))
            //username = newUsername
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
            text = "Change profile picture and username here.",
            color = MaterialTheme.colorScheme.secondary,
            style = MaterialTheme.typography.bodyMedium
        )/*
        if(selectedImageUri == null){
            Image(
                painter = painterResource(R.drawable.kissa),
                contentDescription = "Contact profile picture",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .border(1.5.dp, MaterialTheme.colorScheme.primary, CircleShape)
                    .clickable {
                        pickMedia.launch(
                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                        )
                    }
            )
        }else{*/
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
                viewModel.addInfo(ProfileInfo( 1, selectedImageUri, it))
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
                        viewModel.addInfo(ProfileInfo( 1, selectedImageUri, username))
                        //usernameTextField = username
                        //profilePhoto = selectedImageUri

            //viewModel.updateUsername(newUsername)


                    //}
                //}
                //viewModel.updatePhoto(selectedImageUri.toString())
            }
        ) {
            Text("save changes")
        }
*/
    }


}
