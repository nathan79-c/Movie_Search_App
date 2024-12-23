package com.example.moviesearchapp.util

sealed class Result<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Reussie<T>(data: T?) : Result<T>(data)
    class Echec<T>(message: String?, data: T? = null) : Result<T>(data, message)
    class EnCours<T> : Result<T>()
}
