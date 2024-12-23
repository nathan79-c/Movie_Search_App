package com.example.moviesearchapp.di

import android.content.Context
import androidx.room.Room
import com.example.moviesearchapp.data.AppContainer
import com.example.moviesearchapp.data.DefaultAppContainer
import com.example.moviesearchapp.data.local.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideAppContainer(): AppContainer {
        return DefaultAppContainer()
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