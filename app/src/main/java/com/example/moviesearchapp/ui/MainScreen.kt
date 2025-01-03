package com.example.moviesearchapp.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.moviesearchapp.data.model.MovieModel

@Composable
fun MovieScreen(
    viewModel: MovieViewModel,
    onDetailsClick: (Int) -> Unit = {},
) {
    val uiState by viewModel.uiState.collectAsState()

    var searchQuery by remember { mutableStateOf("") }

    // Charger tous les films au démarrage
    LaunchedEffect(Unit) {
        viewModel.getAllMovies()
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        // Barre de recherche
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { query ->
                searchQuery = query
                if (query.isNotEmpty()) {
                    viewModel.searchMovie(query)
                } else {
                    viewModel.getAllMovies()
                }
            },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Rechercher un film") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Gestion des états
        when (uiState) {
            is UiState.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            is UiState.Success -> {
                val movies = (uiState as UiState.Success).movies
                if (movies.isNotEmpty()) {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(movies) { movie ->
                            MovieItem(movie = movie, onClick = { movie?.id?.let { onDetailsClick(it) } })
                        }
                    }
                } else {
                    Text(
                        text = "Aucun film enregistré",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
            }

            is UiState.Error -> {
                Text(
                    text = (uiState as UiState.Error).message,
                    color = Color.Red,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }

            is UiState.Detail -> {
                val movie = (uiState as UiState.Detail).movie
                if (movie != null) {
                    MovieDetails(movie = movie)
                } else {
                    Text(
                        text = "Film introuvable",
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Composable
fun MovieItem(movie: MovieModel?,onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 8.dp),
       // elevation = 4.dp
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            // Affiche de film
            movie?.posterUrl?.let { posterUrl ->
                AsyncImage(
                    model = posterUrl,
                    contentDescription = "Affiche du film",
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(8.dp))
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Détails du film
            Column {
                Text(
                    text = movie?.title ?: "Titre inconnu",
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    text = "Année : ${movie?.year ?: "Inconnue"}",
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    text = "Genre : ${movie?.genre?.joinToString(", ") ?: "Inconnu"}",
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    text = "Réalisateur : ${movie?.director ?: "Inconnu"}",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@Composable
fun MovieDetails(movie: MovieModel) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        // Affiche de film
        movie.posterUrl?.let { posterUrl ->
            AsyncImage(
                model = posterUrl,
                contentDescription = "Affiche du film",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Détails complets du film
        Text(text = "Titre : ${movie.title}", style = MaterialTheme.typography.bodySmall)
        Text(text = "Année : ${movie.year ?: "Inconnue"}", style = MaterialTheme.typography.bodySmall)
        Text(text = "Durée : ${movie.duration ?: "Inconnue"}", style = MaterialTheme.typography.bodySmall)
        Text(text = "Synopsis : ${movie.synopsis ?: "Non disponible"}", style = MaterialTheme.typography.bodySmall)
        Text(text = "Genres : ${movie.genre.joinToString(", ")}", style = MaterialTheme.typography.bodySmall)
        Text(text = "Réalisateur : ${movie.director ?: "Inconnu"}", style = MaterialTheme.typography.bodySmall)
        Text(text = "Acteurs principaux : ${movie.cast.joinToString(", ")}", style = MaterialTheme.typography.bodySmall)
        Text(text = "Langues : ${movie.languages.joinToString(", ")}", style = MaterialTheme.typography.bodySmall)
        Text(text = "Pays : ${movie.countries.joinToString(", ")}", style = MaterialTheme.typography.bodySmall)
        Text(text = "Récompenses : ${movie.awards ?: "Aucune"}", style = MaterialTheme.typography.bodySmall)
    }
}
