package mx.dev1.movies.presentation.home

import mx.dev1.movies.models.Movie

data class MovieUiState(
    val movieList: List<Movie> = emptyList(),
    val isLoading: Boolean = false,
    val errorEnum: ErrorMessage? = null
)
