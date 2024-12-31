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

    fun getMovieDetails(title: String, apiKey: String = "e031d7af"): Flow<Result<MovieModel>> = flow {
        emit(Result.EnCours())
        try {
            // Appel à l'API
            println("Appel à l'API pour le titre : $title")
            val response = movieApiService.getMovieDetails(apiKey, title)
            if (response.isSuccessful && response.body()?.Response == "True") {
                println("Film trouvé via l'API")
                val movieResponse = response.body()!!
                val movieEntity = movieResponse.toMovieEntity()
                dao.insertMovie(movieEntity)
                emit(Result.Reussie(movieEntity.toMovieModel()))
            } else {
                println("Film introuvable via l'API")
                emit(Result.Echec("Film introuvable sur l'API"))
            }
        } catch (e: Exception) {
            println("Erreur lors de l'appel API : ${e.message}")
            // Recherche dans la base de données en cas d'échec API
            val movieEntity = dao.getMovieByTitle(title)
            if (movieEntity != null) {
                println("Film trouvé dans la base de données")
                emit(Result.Reussie(movieEntity.toMovieModel()))
            } else {
                println("Film introuvable dans la base de données")
                emit(Result.Echec("Film introuvable dans la base de données"))
            }
        }
    }


    suspend fun getMovieById(movieId: Int): Result<MovieModel> = try {
        dao.getMovieById(movieId)?.let { Result.Reussie(it.toMovieModel()) }
            ?: Result.Echec("Film introuvable pour l'ID : $movieId")
    } catch (e: Exception) {
        Result.Echec("Erreur lors de la récupération du film par ID : ${e.message}")
    }

    suspend fun getAllMovies(): Result<List<MovieModel>> = try {
        val movieEntities = dao.getAllMovies()
        if (movieEntities.isNotEmpty()) {
            Result.Reussie(movieEntities.map { it.toMovieModel() })
        } else {
            Result.Echec("Aucun film trouvé dans la base de données")
        }
    } catch (e: Exception) {
        Result.Echec("Erreur lors de la récupération de tous les films : ${e.message}")
    }
}



