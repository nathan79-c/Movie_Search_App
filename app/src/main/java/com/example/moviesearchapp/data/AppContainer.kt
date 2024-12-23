package com.example.moviesearchapp.data

import com.example.moviesearchapp.data.model.repository.MovieRepository
import com.example.moviesearchapp.data.model.repository.NetworkMovieRepository
import com.example.moviesearchapp.data.network.MovieApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit


interface AppContainer {
    val movieRepository: MovieRepository
}

/**
 * Implémentation par défaut pour le conteneur de dépendances à l'échelle de l'application.
 *
 * Les instances sont initialisées paresseusement et partagées dans toute l'application.
 */
class DefaultAppContainer : AppContainer {

    private val apiKey = "e031d7af"// Remplacez par votre clé API OMDb.
    private val baseUrl = "http://www.omdbapi.com/"




    /**
     * Service Retrofit pour créer des appels API OMDb.
     */
    private val movieApiService: MovieApiService by lazy {
        Retrofit.Builder()
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .baseUrl(baseUrl)
            .build()
            .create(MovieApiService::class.java)
    }

    /**
     * Implémentation de l'injection de dépendances pour le référentiel OMDb.
     */
    override val movieRepository: MovieRepository by lazy {
        NetworkMovieRepository(movieApiService, apiKey)
    }
}

