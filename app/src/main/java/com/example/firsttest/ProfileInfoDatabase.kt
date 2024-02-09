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




        fun getInstance(context: Context): ProfileInfoDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                ProfileInfoDatabase::class.java, "ProfileInfo.db")
                .addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)

                        ioThread {
                            getInstance(context).dao().insertInitialItem(ProfileInfo(1, " ", "default name"))
                        }
                    }
                })
                .build()

    }


}