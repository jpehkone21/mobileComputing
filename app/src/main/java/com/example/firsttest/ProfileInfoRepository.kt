package com.example.firsttest

import androidx.lifecycle.LiveData
import com.example.firsttest.ProfileInfo
import com.example.firsttest.ProfileInfoDao

class ProfileInfoRepository(
    private val dao: ProfileInfoDao
) {
    suspend fun insertUser(user: ProfileInfo){
        dao.insertUser(user)
    }/*
    fun getUsername(): String {
        return dao.getUsername()
    }
    fun getPhoto(): String {
        return dao.getPhoto()
    }
    /*
    fun updateUsername(username: String) {
        dao.updateUsername(username)
    }
    fun updatePhoto(photoUri: String) {
        dao.updatePhoto(photoUri)
    }*/
    suspend fun updateInfo(newInfo: ProfileInfo){
        dao.updateInfo(newInfo)
    }*/
    fun getAll(): LiveData<List<ProfileInfo>> = dao.getAll()
    //fun getItemCount(): Int{
    //  return dao.getCount()
    //}

    fun getInfo(id: Long): LiveData<List<ProfileInfo>> = dao.getInfo(id)

}