package com.example.android.movieguidehilt.di


import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.android.movieguidehilt.data.local.MovieDao
import com.example.android.movieguidehilt.data.remote.MovieGuideApi
import com.example.android.movieguidehilt.data.repository.MoviesRepository
import com.example.android.movieguidehilt.data.repository.MoviesRepositoryImpl
import com.example.android.movieguidehilt.db.AppDatabase
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMoviesRepository(api: MovieGuideApi): MoviesRepository {
        return MoviesRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun providesGson(): Gson = GsonBuilder().setLenient().create()

    @Provides
    @Singleton
    fun providesMovieGuideApi(gson: Gson): MovieGuideApi {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(MovieGuideApi::class.java)
    }

}


