package mx.dev1.movies.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import mx.dev1.movies.data.local.FavoriteMovieEntity
import mx.dev1.movies.data.repository.MoviesRepository
import mx.dev1.movies.models.MovieDetail
import mx.dev1.movies.presentation.home.ErrorMessage
import java.net.ConnectException
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val repository: MoviesRepository
) : ViewModel() {
    private val movieDetailUiStateFlow = MutableStateFlow(MovieDetailUiState())
    val movieDetailUiState = movieDetailUiStateFlow.asStateFlow()

    fun getMovieDetails(movieId: String) {
        viewModelScope.launch {
            movieDetailUiStateFlow.update {
                it.copy(isLoading = true, errorEnum = null)
            }

            try {
                val movieDetail = repository.getMovieDetail(movieId = movieId)

                movieDetailUiStateFlow.update {
                    it.copy(
                        movieDetail = movieDetail,
                        isLoading = false,
                        errorEnum = null
                    )
                }

                getFavoriteMovies()
            } catch(e: Exception) {
                val errorEnum = when {
                    e is ConnectException -> ErrorMessage.INTERNET_CONNECTION
                    else -> ErrorMessage.DEFAULT
                }

                movieDetailUiStateFlow.update { movieDetailUiState ->
                    movieDetailUiState.copy(
                        isLoading = false,
                        errorEnum = errorEnum
                    )
                }
            }
        }
    }

    fun updateFavorites(movieDetail: MovieDetail) {
        viewModelScope.launch {
            if(movieDetail.isMovieInFavorites) {
                repository.deleteMovie(
                    FavoriteMovieEntity(
                        movieId = movieDetail.id.toString(),
                        posterPath = movieDetail.posterPath
                    )
                )
            } else {
                repository.insertMovie(
                    FavoriteMovieEntity(
                        movieId = movieDetail.id.toString(),
                        posterPath = movieDetail.posterPath
                    )
                )
            }
        }
    }

    private fun getFavoriteMovies() {
        viewModelScope.launch {
            repository.getFavoriteMovies().collect { favoriteMovieList ->
                val currentMovieDetail = movieDetailUiState.value.movieDetail

                currentMovieDetail?.let {
                    val isMovieInFavorites = favoriteMovieList.any { movieData ->
                        currentMovieDetail.id.toString() == movieData.movieId
                    }

                    movieDetailUiStateFlow.update {
                        it.copy(
                            movieDetail = currentMovieDetail.copy(
                                isMovieInFavorites = isMovieInFavorites
                            )
                        )
                    }
                }
            }
        }
    }
}
