package mx.dev1.movies.presentation.detail

import mx.dev1.movies.models.MovieDetail
import mx.dev1.movies.presentation.home.ErrorMessage

data class MovieDetailUiState(
    val movieDetail : MovieDetail? = null,
    val isLoading: Boolean = false,
    val errorEnum: ErrorMessage? = null
)
