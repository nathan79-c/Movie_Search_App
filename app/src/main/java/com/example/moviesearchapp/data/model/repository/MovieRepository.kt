package com.example.moviesearchapp.data.model.repository

import com.example.moviesearchapp.data.local.dao.MovieDao
import com.example.moviesearchapp.data.model.MovieModel
import com.example.moviesearchapp.data.model.toMovieEntity
import com.example.moviesearchapp.data.model.toMovieModel
import com.example.moviesearchapp.data.network.MovieApiService
import javax.inject.Inject
import com.example.moviesearchapp.util.Result

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieRepository @Inject constructor(
    private val movieApiService: MovieApiService,
    private val dao: MovieDao
) {
    // Recherche un film par titre (avec Flow pour les états)
    fun getMovieDetails(title: String, apiKey: String = "e031d7af"): Flow<Result<MovieModel>> = flow {
        emit(Result.EnCours())

        try {
            // Appel à l'API
            val response = movieApiService.getMovieDetails(apiKey, title)
            if (response.isSuccessful && response.body()?.Response == "True") {
                // Convertir et sauvegarder dans la base de données
                val movieResponse = response.body()!!
                val movieEntity = movieResponse.toMovieEntity()
                dao.insertMovie(movieEntity)
                emit(Result.Reussie(movieEntity.toMovieModel()))
            } else {
                emit(Result.Echec("Film introuvable sur l'API"))
            }
        } catch (e: Exception) {
            try {
                // Rechercher dans la base de données si l'API échoue
                val movieEntity = dao.getMovieByTitle(title)
                if (movieEntity != null) {
                    emit(Result.Reussie(movieEntity.toMovieModel()))
                } else {
                    emit(Result.Echec("Film introuvable dans la base de données"))
                }
            } catch (dbException: Exception) {
                emit(Result.Echec("Erreur lors de l'accès à la base de données : ${dbException.message}"))
            }
        }
    }

    // Recherche un film par ID
    suspend fun getMovieById(movieId: Int): Result<MovieModel> {
        return try {
            val movieEntity = dao.getMovieById(movieId)
            if (movieEntity != null) {
                Result.Reussie(movieEntity.toMovieModel())
            } else {
                Result.Echec("Film introuvable pour l'ID : $movieId")
            }
        } catch (e: Exception) {
            Result.Echec("Erreur lors de la récupération du film par ID : ${e.message}")
        }
    }

    // Récupère tous les films
    suspend fun getAllMovies(): Result<List<MovieModel>> {
        return try {
            val movieEntities = dao.getAllMovies()
            if (movieEntities.isNotEmpty()) {
                val movieModels = movieEntities.map { it.toMovieModel() }
                Result.Reussie(movieModels)
            } else {
                Result.Echec("Aucun film trouvé dans la base de données")
            }
        } catch (e: Exception) {
            Result.Echec("Erreur lors de la récupération de tous les films : ${e.message}")
        }
    }
}


