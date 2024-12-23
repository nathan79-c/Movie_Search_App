package com.example.moviesearchapp.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesearchapp.data.model.MovieModel
import com.example.moviesearchapp.data.model.repository.MovieRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException


class MovieViewModel (private val movieRepository: MovieRepository) : ViewModel(){
    var movieUiState:MovieUiState by mutableStateOf(MovieUiState.Loading)
        private set
    private fun getMovieDetails(){
        viewModelScope.launch {
            try{
                val listResult = movieRepository.fetchMovieDetails()
                movieUiState = MovieUiState.Succes(listResult)
            }catch (e: Exception){
                MovieUiState.Error

            }catch (e: HttpException){
                MovieUiState.Error
            }

        }

    }



}

sealed interface MovieUiState{
    data class Succes(val movies: List<MovieModel>) :MovieUiState
    object Error : MovieUiState
    object Loading : MovieUiState


}
