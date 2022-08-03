package com.example.android.movieguidehilt.di

import android.content.Context
import com.example.android.movieguidehilt.data.local.MovieDao
import com.example.android.movieguidehilt.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object DbModule {


    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Provides
    fun provideUserDao(appDatabase: AppDatabase): MovieDao {
        return appDatabase.movieDatabaseDao()
    }


}
