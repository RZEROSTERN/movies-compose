package mx.dev1.movies.presentation.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import mx.dev1.movies.data.repository.MoviesRepository
import mx.dev1.movies.models.transformToMovieModel
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
        }
    }
}