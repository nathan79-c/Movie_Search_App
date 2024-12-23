package com.example.moviesearchapp.data.model

import com.example.moviesearchapp.data.local.entities.MovieEntity
import com.example.moviesearchapp.data.network.MovieResponse
import kotlinx.serialization.Serializable

@Serializable
data class MovieModel(
        val id: Int = 0, // Identifiant unique pour chaque film
        val title: String, // Titre du film
        val year: Int? = null, // Année de sortie
        val rating: String? = null, // Classement ou certification
        val releaseDate: String? = null, // Date de sortie au format "YYYY-MM-DD"
        val duration: String? = null, // Durée du film
        val genre: List<String> = emptyList(), // Liste des genres
        val director: String? = null, // Réalisateur
        val writers: List<String> = emptyList(), // Liste des scénaristes
        val cast: List<String> = emptyList(), // Liste des acteurs principaux
        val synopsis: String? = null, // Résumé ou intrigue
        val languages: List<String> = emptyList(), // Liste des langues
        val countries: List<String> = emptyList(), // Liste des pays d'origine
        val awards: String? = null, // Récompenses et nominations
        val posterUrl: String? = null // URL de l'affiche du film
    )

fun MovieResponse.toMovieModel(): MovieModel {
    return MovieModel(
        title = Title ?: "Titre inconnu",
        year = Year?.toIntOrNull(),
        rating = Rated,
        releaseDate = Released,
        duration = Runtime,
        genre = Genre?.split(", ") ?: emptyList(),
        director = Director,
        writers = Writer?.split(", ") ?: emptyList(),
        cast = Actors?.split(", ") ?: emptyList(),
        synopsis = Plot,
        languages = Language?.split(", ") ?: emptyList(),
        countries = Country?.split(", ") ?: emptyList(),
        awards = Awards,
        posterUrl = Poster
    )
}

fun MovieResponse.toMovieEntity(): MovieEntity {
    return MovieEntity(
        title = Title ?: "Titre inconnu",
        year = Year?.toIntOrNull(),
        rating = Rated,
        releaseDate = Released,
        duration = Runtime,
        genre = Genre ?: "",
        director = Director,
        writers = Writer ?: "",
        cast = Actors ?: "",
        synopsis = Plot,
        languages = Language ?: "",
        countries = Country ?: "",
        awards = Awards,
        posterUrl = Poster
    )
}


fun MovieEntity.toMovieModel(): MovieModel {
    return MovieModel(
        id = id,
        title = title,
        year = year,
        rating = rating,
        releaseDate = releaseDate,
        duration = duration,
        genre = genre!!.split(", ").filter { it.isNotBlank() },
        director = director,
        writers = writers!!.split(", ").filter { it.isNotBlank() },
        cast = cast!!.split(", ").filter { it.isNotBlank() },
        synopsis = synopsis,
        languages = languages!!.split(", ").filter { it.isNotBlank() },
        countries = countries!!.split(", ").filter { it.isNotBlank() },
        awards = awards,
        posterUrl = posterUrl
    )
}





