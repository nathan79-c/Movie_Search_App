package com.example.moviesearchapp.data.model.repository

import com.example.moviesearchapp.data.model.MovieModel
import com.example.moviesearchapp.data.network.MovieApiService
import com.example.moviesearchapp.data.network.SearchResponse

interface MovieRepository {
    suspend fun fetchMovieDetails(title: String): MovieModel
    suspend fun searchMovies(query: String): SearchResponse
}

class NetworkMovieRepository(
    private val movieApiService: MovieApiService,
    private val apiKey: String
):MovieRepository{
    override suspend fun fetchMovieDetails(title: String): MovieModel {
        return movieApiService.getMovieDetails(apiKey, title)
    }

    override suspend fun searchMovies(query: String): SearchResponse {
        return movieApiService.getMoviesBySearch(apiKey, query)
    }

}