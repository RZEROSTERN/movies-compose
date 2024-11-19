package mx.dev1.movies.presentation.favorites

import android.database.sqlite.SQLiteException
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import mx.dev1.movies.data.repository.MoviesRepository
import mx.dev1.movies.models.Movie
import mx.dev1.movies.models.transformToMovieEntity
import mx.dev1.movies.models.transformToMovieModel
import mx.dev1.movies.presentation.home.ErrorMessage
import java.net.ConnectException
import javax.inject.Inject

@HiltViewModel
class FavoriteMoviesViewModel @Inject constructor(
    private val repository: MoviesRepository
): ViewModel() {
    private val favoriteUiStateFlow = MutableStateFlow(FavoriteMoviesUiState())
    val favoriteUiState = favoriteUiStateFlow.asStateFlow()

    init {
        getFavoriteMovies()
    }

    private fun getFavoriteMovies() {
        viewModelScope.launch {
            favoriteUiStateFlow.update {
                it.copy(isLoading = true)
            }

            try {
                repository.getFavoriteMovies().collect { favoriteMovieList ->
                    favoriteUiStateFlow.update {
                        it.copy(
                            isLoading = false,
                            movieList = favoriteMovieList.map { movieEntity ->
                                movieEntity.transformToMovieModel()
                            }
                        )
                    }
                }
            } catch(e: Exception) {
                val errorEnum = when {
                    e is ConnectException -> ErrorMessage.INTERNET_CONNECTION
                    e is SQLiteException -> ErrorMessage.DATABASE
                    else -> ErrorMessage.DEFAULT
                }

                favoriteUiStateFlow.update {
                    it.copy(
                        isLoading = false,
                        errorEnum = errorEnum
                    )
                }
            }
        }
    }

    fun deleteMovieFromFavorites(movie: Movie) {
        viewModelScope.launch {
            repository.deleteMovie(
                movieEntity = movie.transformToMovieEntity()
            )
        }
    }
}