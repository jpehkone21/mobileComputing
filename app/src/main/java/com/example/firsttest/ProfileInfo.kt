package com.example.firsttest

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProfileInfo(
    @PrimaryKey (autoGenerate = true)
    val id: Long,
    var photoUri: String,
    var username: String
){

}
