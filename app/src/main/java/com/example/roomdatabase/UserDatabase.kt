package com.example.roomdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao?

    companion object {
        private const val DATABASE_NAME = "user.db"
        private var instance: UserDatabase? = null
        @Synchronized
        fun getInstance(context: Context): UserDatabase? {
            if (instance == null) {
                instance = Room.databaseBuilder(context.applicationContext,
                    UserDatabase::class.java, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build()
            }
            return instance
        }
    }
}