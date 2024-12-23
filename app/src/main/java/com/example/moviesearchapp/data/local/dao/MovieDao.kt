package com.example.moviesearchapp.data.local.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moviesearchapp.data.local.entities.MovieEntity

interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovie(movieEntity: MovieEntity)

    @Query("SELECT * FROM movies_entity WHERE id = :movieId")
    suspend fun getMovieById(movieId: Int): MovieEntity?

    @Query("SELECT * FROM movies_entity")
    suspend fun getAllMovies(): List<MovieEntity>

    @Query("SELECT * FROM movies_entity WHERE title = :title LIMIT 1")
    suspend fun getMovieByTitle(title: String): MovieEntity?


}