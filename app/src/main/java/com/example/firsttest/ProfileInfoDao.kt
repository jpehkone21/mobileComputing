package com.example.firsttest

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert

@Dao
interface ProfileInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: ProfileInfo)

    @Query("SELECT COUNT(*) FROM ProfileInfo")
    fun isEmpty(): Int
    @Insert
    fun insertInitialItem(item: ProfileInfo)

    @Query("SELECT * FROM profileinfo")
    fun getAll(): LiveData<List<ProfileInfo>>

    @Query("SELECT * FROM profileinfo WHERE id= :id")
    fun getInfo(id: Long): LiveData<List<ProfileInfo>>



}