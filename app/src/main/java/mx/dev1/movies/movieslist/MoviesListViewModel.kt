package mx.dev1.movies.movieslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import mx.dev1.movies.data.MovieUiState
import mx.dev1.movies.data.MoviesRepository

class MoviesListViewModel(
    private val repository: MoviesRepository
): ViewModel() {
    private val movieListUiStateFlow = MutableStateFlow(MovieUiState())
    val movieListUiState: StateFlow<MovieUiState> = movieListUiStateFlow.asStateFlow()

    init {
        getMovies()
    }

    private fun getMovies() {
        viewModelScope.launch {
            movieListUiStateFlow.update {
                it.copy(
                    isLoading = true
                )
            }

            delay(2000L) // Response simulation, please delete

            val movies =  repository.getMovies()// Change to repository implementation

            movieListUiStateFlow.update { moviesUiState ->
                moviesUiState.copy(
                    movieList = movies,
                    isLoading = false
                )
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                MoviesListViewModel (
                    MoviesRepository()
                )
            }
        }
    }
}
