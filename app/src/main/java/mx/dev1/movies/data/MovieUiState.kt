package mx.dev1.movies.data

import mx.dev1.movies.models.Movie
import mx.dev1.movies.presentation.home.ErrorMessage

data class MovieUiState(
    val movieList: List<Movie> = emptyList(),
    val isLoading: Boolean = false,
    val errorEnum: ErrorMessage? = null
)
