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




}

sealed class Result<T>(data: T? = null, message: String? = null) {
    class  Succes<T>(data: T?): Result<T>(data)
    class Error<T>(message: String?, data: T? = null): Result<T>(data, message)
    class Loading<T>: Result<T>()

}
