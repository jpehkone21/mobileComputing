package com.example.firsttest

import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

class databaseCallback(private val dao: ProfileInfoDao) : RoomDatabase.Callback()  {
    private var initialized = false

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        if (!initialized) {
            val item = ProfileInfo(1, "", "Matti")
            dao.insertInitialItem(item)
            initialized = true
        }
    }



}