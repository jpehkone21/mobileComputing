package com.example.firsttest

import androidx.lifecycle.LiveData
import com.example.firsttest.ProfileInfo
import com.example.firsttest.ProfileInfoDao

class ProfileInfoRepository(
    private val dao: ProfileInfoDao
) {
    suspend fun insertUser(user: ProfileInfo){
        dao.insertUser(user)
    }
    fun isEmpty(): Boolean {
        return dao.isEmpty() == 0 // Add a method in your DAO to check if the database is empty
    }
    fun insertInitialItem(item: ProfileInfo){
        dao.insertInitialItem(item)
    }

    fun getAll(): LiveData<List<ProfileInfo>> = dao.getAll()


    fun getInfo(id: Long): LiveData<List<ProfileInfo>> = dao.getInfo(id)

}