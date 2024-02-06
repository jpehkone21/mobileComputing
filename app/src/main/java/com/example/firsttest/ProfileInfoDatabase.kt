package com.example.firsttest

import android.content.Context
import androidx.lifecycle.viewModelScope
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [ProfileInfo::class], version = 1)
abstract class ProfileInfoDatabase : RoomDatabase() {
    abstract fun dao(): ProfileInfoDao

    companion object{
        @Volatile
        private var INSTANCE: ProfileInfoDatabase? = null

        fun getDatabase(context: Context): ProfileInfoDatabase {

            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ProfileInfoDatabase::class.java,
                    "profileInfo.dp"
                ).build()

                    INSTANCE = instance
                return instance
            }
        }

    }
}