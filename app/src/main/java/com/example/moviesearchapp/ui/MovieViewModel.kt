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

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState

    // Recherche d'un film par titre
    fun searchMovie(title: String) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            movieRepository.getMovieDetails(title).collect { result ->
                _uiState.value = when (result) {
                    is Result.EnCours -> UiState.Loading
                    is Result.Reussie -> UiState.Success(listOf(result.data))
                    is Result.Echec -> UiState.Error(result.message ?: "Erreur inconnue")
                }
            }
        }
    }

    // Récupération de tous les films enregistrés
    fun getAllMovies() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            val result = movieRepository.getAllMovies()
            _uiState.value = when (result) {
                is Result.Reussie -> result.data?.let { UiState.Success(it) }!!
                is Result.Echec -> UiState.Error(result.message ?: "Erreur inconnue")
                is Result.EnCours -> TODO()
            }
        }
    }

    // Détails d'un film par ID
    fun getMovieDetails(movieId: Int) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            val result = movieRepository.getMovieById(movieId)
            _uiState.value = when (result) {
                is Result.Reussie -> UiState.Detail(result.data)
                is Result.Echec -> UiState.Error(result.message ?: "Erreur inconnue")
                is Result.EnCours ->  UiState.Loading
            }
        }
    }
}

sealed class UiState {
    object Loading : UiState()
    data class Success(val movies: List<MovieModel?>) : UiState()
    data class Error(val message: String) : UiState()
    data class Detail(val movie: MovieModel?) : UiState()
}


