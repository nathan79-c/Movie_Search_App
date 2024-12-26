package com.example.moviesearchapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesearchapp.data.model.MovieModel
import com.example.moviesearchapp.data.model.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import com.example.moviesearchapp.util.Result
import kotlinx.coroutines.launch


@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    // État pour la liste de tous les films
    private val _uiStateAllMovies = MutableStateFlow<MovieUiState>(MovieUiState.Loading)
    val uiStateAllMovies: StateFlow<MovieUiState> = _uiStateAllMovies

    // État pour les détails d'un film
    private val _uiStateMovieDetails = MutableStateFlow<MovieUiState>(MovieUiState.Loading)
    val uiStateMovieDetails: StateFlow<MovieUiState> = _uiStateMovieDetails

    /**
     * Recherche un film par titre
     */
    fun fetchMovieDetailsByTitle(title: String) {
        viewModelScope.launch {
            _uiStateMovieDetails.value = MovieUiState.Loading
            movieRepository.getMovieDetails(title).collect { result ->
                _uiStateMovieDetails.value = when (result) {
                    is Result.Reussie -> MovieUiState.Success(result.data!!)
                    is Result.Echec -> MovieUiState.Error(result.message ?: "Erreur inconnue")
                    is Result.EnCours -> MovieUiState.Loading
                }
            }
        }
    }

    /**
     * Obtenir les détails d'un film grâce à son ID
     */
    fun fetchMovieDetailsById(movieId: Int) {
        viewModelScope.launch {
            _uiStateMovieDetails.value = MovieUiState.Loading
            val result = movieRepository.getMovieById(movieId)
            _uiStateMovieDetails.value = when (result) {
                is Result.Reussie -> MovieUiState.Success(result.data!!)
                is Result.Echec -> MovieUiState.Error(result.message ?: "Erreur inconnue")
                is Result.EnCours -> MovieUiState.Loading
            }
        }
    }

    /**
     * Obtenir tous les films enregistrés
     */
    fun fetchAllMovies() {
        viewModelScope.launch {
            _uiStateAllMovies.value = MovieUiState.Loading
            val result = movieRepository.getAllMovies()
            _uiStateAllMovies.value = when (result) {
                is Result.Reussie -> MovieUiState.Success(result.data!!)
                is Result.Echec -> MovieUiState.Error(result.message ?: "Erreur inconnue")
                is Result.EnCours -> MovieUiState.Loading
            }
        }
    }

    init {
        fetchAllMovies()
    }
}



sealed class MovieUiState {
    object Loading : MovieUiState() // État de chargement
    data class Success<T>(val data: T) : MovieUiState() // Succès avec données génériques
    data class Error(val message: String) : MovieUiState() // État d'erreur
}


