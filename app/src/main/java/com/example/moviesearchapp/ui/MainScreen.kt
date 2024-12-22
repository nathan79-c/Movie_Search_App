package com.example.moviesearchapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.moviesearchapp.R

@Composable
fun ExploreScreen() {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background) // Background from theme
                .padding(innerPadding)
        ) {


            // Banner
            Banner()

            Spacer(modifier = Modifier.height(16.dp))

            // Search Bar
            SearchBar()

            Spacer(modifier = Modifier.height(16.dp))

            // Explore Header
            Text(
                text = "Explore",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground, // Text color from theme
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Movie Collections Grid
            MovieCollectionGrid()
        }
    }
}

@Composable
fun Banner() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clip(MaterialTheme.shapes.medium) // Shape from theme
            .background(
                Brush.horizontalGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.primary,
                        MaterialTheme.colorScheme.secondary
                    )
                )
            )
    ) {
        Text(
            text = "Discover Amazing Movies",
            color = MaterialTheme.colorScheme.onPrimary, // Text color for primary background
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(16.dp)
        )
    }
}

@Composable
fun SearchBar() {
    TextField(
        value = TextFieldValue(""), // Initial value
        onValueChange = { /* Handle text input */ },
        placeholder = {
            Text(
                text = "Search",
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search Icon",
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clip(MaterialTheme.shapes.medium),

        singleLine = true
    )
}


@Composable
fun MovieCollectionGrid() {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(6) { index ->
            val movieData = when (index) {
                0 -> Pair("Mickey Mouse & Friends", R.drawable.movie_poster)
                1 -> Pair("Black Widow", R.drawable.t_l_chargement__2_)
                2 -> Pair("Beasts and Monsters", R.drawable.movie_poster)
                3 -> Pair("Loki", R.drawable.uncharted__drake_s_fortune__2007_)
                4 -> Pair("Frozen", R.drawable.movie_poster)
                else -> Pair("Unknown Collection", R.drawable.movie_poster)
            }
            MovieCollectionCard(title = movieData.first, imageRes = movieData.second)
        }
    }
}

@Composable
fun MovieCollectionCard(title: String, imageRes: Int) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .clip(MaterialTheme.shapes.medium) // Shape from theme
            .background(MaterialTheme.colorScheme.surface)
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = title,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            MaterialTheme.colorScheme.surface.copy(alpha = 0.7f)
                        ),
                        startY = 200f
                    )
                )
        )
        Text(
            text = title,
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(8.dp)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun MainScreenPreview(){
    ExploreScreen()
}

