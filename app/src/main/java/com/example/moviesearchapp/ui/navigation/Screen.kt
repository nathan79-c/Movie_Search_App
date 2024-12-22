package com.example.moviesearchapp.ui.navigation

import kotlinx.serialization.Serializable

sealed interface Screen {
@Serializable
data object ExploreScreen : Screen

 @Serializable
 data class Details(val movieId: Int) : Screen
}