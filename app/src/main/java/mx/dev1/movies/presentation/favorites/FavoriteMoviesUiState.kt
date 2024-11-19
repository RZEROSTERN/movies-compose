package mx.dev1.movies.presentation.favorites

import mx.dev1.movies.models.Movie
import mx.dev1.movies.presentation.home.ErrorMessage

data class FavoriteMoviesUiState(
    val isLoading: Boolean = false,
    val movieList: List<Movie> = emptyList(),
    val errorMessage: ErrorMessage? = null
)
