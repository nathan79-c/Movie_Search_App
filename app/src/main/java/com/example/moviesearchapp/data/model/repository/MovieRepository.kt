package com.example.moviesearchapp.data.model.repository

import com.example.moviesearchapp.data.model.MovieModel
import com.example.moviesearchapp.data.network.MovieApiService

interface MovieRepository {
    suspend fun fetchMovieDetails(title: String): MovieModel

}

class NetworkMovieRepository(
    private val movieApiService: MovieApiService,
    private val apiKey: String
):MovieRepository{
    override suspend fun fetchMovieDetails(title: String): MovieModel {
        return movieApiService.getMovieDetails(apiKey, title)
    }

}