package com.example.moviesearchapp.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.example.moviesearchapp.data.model.MovieModel
import com.example.moviesearchapp.data.network.MovieApi
import kotlinx.coroutines.launch


var movieUiState:MovieUiState by mutableStateOf(MovieUiState.Loading)
    private set
private fun getMovieDetails(){
    viewModelScope.launch {
        try{
            val listResult = MovieApi.retrofitService.getMovieDetails()
            movieUiState = MovieUiState.Succes(listResult)
        }catch (e: Exception){
          MovieUiState.Error

        }

    }

}

sealed interface MovieUiState{
    data class Succes(val movies: List<MovieModel>) :MovieUiState
    object Error : MovieUiState
    object Loading : MovieUiState


}