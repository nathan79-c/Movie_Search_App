package com.example.moviesearchapp.data.network

import com.example.moviesearchapp.data.model.MovieModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL =
 "https://www.omdbapi.com/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()


interface MovieApiService {

    @GET("/")
    suspend fun getMovieDetails(
        @Query("apikey") apiKey: String,
        @Query("t") title: String
    ): MovieResponse
}


data class MovieResponse(
    val Title: String?,
    val Year: String?,
    val Rated: String?,
    val Released: String?,
    val Runtime: String?,
    val Genre: String?,
    val Director: String?,
    val Writer: String?,
    val Actors: String?,
    val Plot: String?,
    val Language: String?,
    val Country: String?,
    val Awards: String?,
    val Poster: String?,
    val Ratings: List<Rating>?,
    val Metascore: String?,
    val imdbRating: String?,
    val imdbVotes: String?,
    val imdbID: String?,
    val Type: String?,
    val DVD: String?,
    val BoxOffice: String?,
    val Production: String?,
    val Website: String?,
    val Response: String?
)

data class Rating(
    val Source: String?,
    val Value: String?
)


object MovieApiInstance{
    private const val BASE_URL =
        "https://www.omdbapi.com/"

    val api: MovieApiService by lazy{
         Retrofit.Builder()
            .addConverterFactory(ScalarsConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
             .create(MovieApiService::class.java)
    }
}

fun fetchMovieDetails(apiKey: String, movieTitle: String) {
    CoroutineScope(Dispatchers.IO).launch {
        try {
            val response = MovieApiInstance.api.getMovieDetails(apiKey, movieTitle)
            if (response.Response == "True") {
                println("Title: ${response.Title}")
                println("Year: ${response.Year}")
                println("Genre: ${response.Genre}")
                println("Plot: ${response.Plot}")
                println("Poster URL: ${response.Poster}")
            } else {
                println("Error: Movie not found")
            }
        } catch (e: Exception) {
            println("Error: ${e.message}")
        }
    }
}


