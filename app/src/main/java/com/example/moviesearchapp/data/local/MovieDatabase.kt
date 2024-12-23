package com.example.moviesearchapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.moviesearchapp.data.local.dao.MovieDao
import com.example.moviesearchapp.data.local.entities.MovieEntity

@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
abstract class MovieDatabase: RoomDatabase() {
    abstract fun movieDao(): MovieDao
}