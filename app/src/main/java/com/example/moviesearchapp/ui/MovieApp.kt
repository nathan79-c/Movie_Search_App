package com.example.moviesearchapp.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun MovieApp(){
    val navController = rememberNavController()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()

    NavHost(
        navController = navController,

    ) { }


}


sealed class