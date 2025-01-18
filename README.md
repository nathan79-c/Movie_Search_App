# Movie Search App

The **Movie Search App** is an Android project that allows users to search for movies by title using an external API (OMDb API) and stores the results locally in a Room database. The app uses a modern architecture with components such as Retrofit for network calls, Room for data persistence, and Kotlin Coroutines for managing asynchronous operations.

## Features

- **Search for movies by title**: Users can search for a movie by its title. The app first queries the OMDb API, and if the movie is found, it is stored locally in the database.
- **Retrieve movie details**: Movie details (title, year, genre, etc.) are displayed after a successful search.
- **Local storage**: Searched movies are stored locally in a Room database for offline access.
- **Error handling**: If the API call fails, the app attempts to retrieve the movie from the local database.

## Architecture

The app follows the **MVVM (Model-View-ViewModel)** architecture with the following components:

- **Repository**: The `MovieRepository` is responsible for managing data. It interacts with the network API and the local database.
- **ViewModel**: The ViewModel exposes data to the UI and handles presentation logic.
- **View (UI)**: Activities and fragments display data and interact with the user.
- **API Service**: Uses Retrofit to make network calls to the OMDb API.
- **Local Database**: Uses Room to store searched movies.

## Technologies Used

- **Kotlin**: Primary programming language.
- **Retrofit**: For network calls to the OMDb API.
- **Room**: For local data persistence.
- **Kotlin Coroutines**: For managing asynchronous operations.
- **Dagger/Hilt**: For dependency injection.
- **Flow**: For reactive data streams.

## Project Structure

The project is organized as follows:

com.example.moviesearchapp
├── data
│ ├── local
│ │ ├── dao
│ │ │ └── MovieDao.kt
│ │ └── entity
│ │ └── MovieEntity.kt
│ ├── model
│ │ ├── MovieModel.kt
│ │ └── repository
│ │ └── MovieRepository.kt
│ └── network
│ ├── MovieApiService.kt
│ └── response
│ └── MovieResponse.kt
├── di
│ └── AppModule.kt
├── ui
│ ├── viewmodel
│ │ └── MovieViewModel.kt
│ └── MainActivity.kt
└── util
└── Result.kt
