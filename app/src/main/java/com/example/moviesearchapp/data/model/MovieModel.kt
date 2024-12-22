package com.example.moviesearchapp.data.model


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



