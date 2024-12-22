package com.example.moviesearchapp.data.network

import com.example.moviesearchapp.data.model.MovieModel
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {
    @GET("/")
    suspend fun getMovieDetails(
        @Query("apikey") apiKey: String,
        @Query("t") title: String
    ): MovieModel

    @GET("/")
    suspend fun getMoviesBySearch(
        @Query("apikey") apiKey: String,
        @Query("s") query: String
    ): SearchResponse
}


data class SearchResponse(
    val Search: List<SearchResult>?,
    val totalResults: String?,
    val Response: String
)

data class SearchResult(
    val Title: String,
    val Year: String,
    val imdbID: String,
    val Type: String,
    val Poster: String
)
