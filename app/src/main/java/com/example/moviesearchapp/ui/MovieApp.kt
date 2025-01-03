package com.example.moviesearchapp.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.moviesearchapp.ui.navigation.Screen


@Composable
fun MovieApp(){
    val navController = rememberNavController()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val viewModel = hiltViewModel<MovieViewModel>()

    NavHost(
        navController = navController,
        startDestination = Screen.ExploreScreen

    ) {
            composable<Screen.ExploreScreen> {

                MovieScreen(
                   viewModel = viewModel,
                    onDetailsClick ={ movieId ->
                        navController.navigate(Screen.Details(movieId))
                    }
                )
            }
        composable<Screen.Details> {backStackEntry ->
            val movie:Screen.Details = backStackEntry.toRoute()
            MovieDetailsScreen(
                navController = navController,
                movieId = movie.movieId,
                viewModel = viewModel
            )
        }
    }

}

