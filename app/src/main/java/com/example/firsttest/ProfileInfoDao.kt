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
    /*
    @Update
    fun updatePhoto(newPhotoUri: String)
    @Update
    fun updateUsername(username: String)


    @Update
    suspend fun updateInfo(newInfo: ProfileInfo)

    @Query("SELECT photoUri FROM profileinfo WHERE id = 1")
    fun getPhoto():String

    @Query("SELECT username FROM profileinfo WHERE id = 1")
    fun getUsername():String
*/
    @Query("SELECT * FROM profileinfo")
    fun getAll(): LiveData<List<ProfileInfo>>

    @Query("SELECT * FROM profileinfo WHERE id= :id")
    fun getInfo(id: Long): LiveData<List<ProfileInfo>>

    //@Query("SELECT COUNT(id) From profileinfo")
    //fun getCount():Int

}