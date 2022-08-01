package com.example.android.movieguidehilt.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.android.movieguidehilt.data.local.Movie
import com.example.android.movieguidehilt.data.local.MovieDao

@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun movieDatabaseDao(): MovieDao

    //abstract fun userRemoteKeyDao(): UserRemoteKeyDAO
//    companion object {
//
//        @Volatile
//        private var INSTANCE: AppDatabase? = null
//
//        fun getInstance(context: Context): AppDatabase {
//
//            synchronized(this) {
//                var instance = INSTANCE
//
//                if (instance == null) {
//                    instance = Room.databaseBuilder(
//                        context.applicationContext,
//                        AppDatabase::class.java,
//                        "netflix_app_database"
//                    ).fallbackToDestructiveMigration()
//                        .build()
//                    INSTANCE = instance
//                }
//                return instance
//            }
//
//
//        }
//    }

}