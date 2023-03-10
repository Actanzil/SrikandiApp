package com.example.srikandiapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Item::class],
    version = 2
)
abstract class ItemDB : RoomDatabase(){

    abstract fun itemDao() : ItemDao

    companion object {

        @Volatile private var instance : ItemDB? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            ItemDB::class.java,
            "dbsrikandi.db"
        )
            .fallbackToDestructiveMigration()
            .build()

    }
}