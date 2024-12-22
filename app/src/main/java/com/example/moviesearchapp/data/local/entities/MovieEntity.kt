package com.example.moviesearchapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class Movie(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0, // Identifiant unique pour chaque film, auto-généré

    val title: String, // Titre du film
    val year: Int?, // Année de sortie (nullable pour gérer des cas d'absence)
    val rating: String?, // Classement ou certification (nullable)
    val releaseDate: String?, // Date de sortie du film au format "YYYY-MM-DD" (nullable)
    val duration: String?, // Durée du film (nullable)
    val genre: String?, // Genres, séparés par des virgules (nullable)
    val director: String?, // Réalisateur (nullable)
    val writers: String?, // Liste des scénaristes, séparés par des virgules (nullable)
    val cast: String?, // Liste des acteurs principaux (nullable)
    val synopsis: String?, // Résumé ou intrigue (nullable)
    val languages: String?, // Langues, séparées par des virgules (nullable)
    val countries: String?, // Pays d'origine, séparés par des virgules (nullable)
    val awards: String?, // Récompenses et nominations (nullable)
    val posterUrl: String? // URL de l'affiche du film (nullable)
)
