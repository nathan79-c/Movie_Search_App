package com.example.moviesearchapp.di

import android.content.Context
import androidx.room.Room
import com.example.moviesearchapp.data.local.MovieDatabase
import com.example.moviesearchapp.data.network.MovieApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providerRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://www.omdbapi.com/")
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()

    }

    @Singleton
    @Provides
    fun provideMovieApiService(retrofit: Retrofit): MovieApiService {
        return retrofit.create(MovieApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideRoomDatabase(@ApplicationContext context: Context): MovieDatabase{
        return Room.databaseBuilder(
            context.applicationContext,
            MovieDatabase::class.java,
            "movie_database"
        ).build()

    }

    @Provides
    fun provideMovieDao(movieDatabase: MovieDatabase) = movieDatabase.movieDao()


}