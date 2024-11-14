package mx.dev1.movies.data

import mx.dev1.movies.models.Movie

data class MovieUiState(
    val movieList: List<Movie> = emptyList(),
    val isLoading: Boolean = false,
    val showErrorMessage: Boolean = false
)
