package com.example.moviesearchapp.data

import com.example.moviesearchapp.data.model.repository.MovieRepository
import com.example.moviesearchapp.data.model.repository.NetworkMovieRepository
import com.example.moviesearchapp.data.network.MovieApiService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory


interface AppContainer {
    val movieRepository: MovieRepository
}

/**
 * Implémentation par défaut pour le conteneur de dépendances à l'échelle de l'application.
 *
 * Les instances sont initialisées paresseusement et partagées dans toute l'application.
 */
class DefaultAppContainer : AppContainer {

    private val apiKey = "tt3896198&apikey=e031d7af" // Remplacez par votre clé API OMDb.
    private val baseUrl = "http://www.omdbapi.com/"

    /**
     * Configuration d'un client HTTP avec des paramètres personnalisés (si nécessaire).
     */
    private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .build()

    /**
     * Utilise le constructeur de Retrofit pour créer un objet Retrofit.
     */
    private val retrofit: Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .addConverterFactory(ScalarsConverterFactory.create())
        .baseUrl(baseUrl)
        .build()

    /**
     * Service Retrofit pour créer des appels API OMDb.
     */
    private val movieApiService: MovieApiService by lazy {
        retrofit.create(MovieApiService::class.java)
    }

    /**
     * Implémentation de l'injection de dépendances pour le référentiel OMDb.
     */
    override val movieRepository: MovieRepository by lazy {
        NetworkMovieRepository(movieApiService, apiKey)
    }
}

