package com.stepasha.keyconservation.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.stepasha.keyconservation.model.User

@Database(entities = [User::class], version = 3, exportSchema = false)
abstract class AppDB : RoomDatabase(){



    abstract fun userDao() : UserDAO


    companion object {
        // Singleton prevents multiple instances of database from opening at the same time
        @Volatile
        private var INSTANCE: AppDB? = null

        fun getDatabase(context: Context) : AppDB {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDB::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}