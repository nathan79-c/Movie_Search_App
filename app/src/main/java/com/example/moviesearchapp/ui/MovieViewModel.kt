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

    // État pour les détails d'un film
    private val _movieState = MutableStateFlow<Result<MovieModel>>(Result.EnCours())
    val movieState: StateFlow<Result<MovieModel>> = _movieState

    // État pour la liste de tous les films
    private val _allMoviesState = MutableStateFlow<Result<List<MovieModel>>>(Result.EnCours())
    val allMoviesState: StateFlow<Result<List<MovieModel>>> = _allMoviesState

    /**
     * Recherche un film par titre (via API ou base de données)
     */
    fun fetchMovieDetailsByTitle(title: String) {
        viewModelScope.launch {
            movieRepository.getMovieDetails(title).collect { result ->
                _movieState.value = result
            }
        }
    }

    /**
     * Obtenir les détails d'un film grâce à son ID
     */
    fun fetchMovieDetailsById(movieId: Int) {
        viewModelScope.launch {
            _movieState.value = Result.EnCours()
            val result = movieRepository.getMovieById(movieId)
            _movieState.value = result
        }
    }

    /**
     * Obtenir tous les films enregistrés dans la base de données
     */
    fun fetchAllMovies() {
        viewModelScope.launch {
            _allMoviesState.value = Result.EnCours()
            val result = movieRepository.getAllMovies()
            _allMoviesState.value = result
        }
    }
}



