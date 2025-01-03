package com.example.moviesearchapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.moviesearchapp.data.model.MovieModel

@Composable
fun MovieDetailsScreen(
    navController: NavController,
    movieId: Int,
    viewModel: MovieViewModel
) {
    // Observer l'état actuel
    val uiState by viewModel.uiState.collectAsState()

    // Charger les détails du film lorsque l'écran apparaît
    LaunchedEffect(movieId) {
        viewModel.getMovieDetails(movieId)
    }

    // Affichage de l'écran en fonction de l'état
    Box(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        when (uiState) {
            is UiState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center)) // Indicateur de chargement
            }

            is UiState.Detail -> {
                val movie = (uiState as UiState.Detail).movie
                if (movie != null) {
                    MovieDetail(movie = movie) // Affichage des détails du film
                } else {
                    Text(
                        text = "Film introuvable",
                        style = MaterialTheme.typography.bodySmall,
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

            else -> {}
        }

        // Bouton pour revenir à l'écran précédent
        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
        ) {
            Text(text = "Retour")
        }
    }
}

@Composable
fun MovieDetail(movie: MovieModel) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            // Affiche le poster du film
            movie.posterUrl?.let { posterUrl ->
                AsyncImage(
                    model = posterUrl,
                    contentDescription = "Affiche du film",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp)
                        .clip(RoundedCornerShape(12.dp))
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Titre du film
            Text(
                text = movie.title,
                style = MaterialTheme.typography.displayMedium,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Détails du film
            Text(
                text = "Année : ${movie.year ?: "Inconnue"}",
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = "Genre : ${movie.genre.joinToString(", ") ?: "Inconnu"}",
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = "Réalisateur : ${movie.director ?: "Inconnu"}",
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Synopsis
            Text(
                text = "Synopsis :",
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = movie.synopsis ?: "Synopsis non disponible",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}