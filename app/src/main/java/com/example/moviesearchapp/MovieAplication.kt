package com.example.moviesearchapp

import android.app.Application
import com.example.moviesearchapp.data.AppContainer
import com.example.moviesearchapp.data.DefaultAppContainer

class MovieAplication: Application(){
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()

    }
}