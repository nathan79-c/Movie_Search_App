package com.example.moviesearchapp.data.model.repository

import com.example.moviesearchapp.data.local.dao.MovieDao
import com.example.moviesearchapp.data.model.MovieModel
import com.example.moviesearchapp.data.network.MovieApiService
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieApiService: MovieApiService,
    private val dao: MovieDao

){
    suspend fun getMovieDetails(title: String, apiKey: String = "e031d7af"): MovieModel {
        return try {
            // Récupérer les données depuis l'API
            val response = movieApiService.getMovieDetails(apiKey, title)
            if (response.isSuccessful && response.body()?.Response == "True") {
                // Convertir en MovieEntity et sauvegarder dans la base de données
                val movieEntity = response.toMovieEntity()
                movieDao.insertMovie(movieEntity)
                // Retourner MovieModel
                movieEntity.toMovieModel()
            } else {
                throw Exception("Film introuvable sur l'API")
            }
        } catch (e: Exception) {
            // En cas d'échec de l'API, chercher dans la base de données
            val movieEntity = movieDao.getMovieByTitle(title)
                ?: throw Exception("Film introuvable dans la base de données")
            movieEntity.toMovieModel()
        }
    }
}