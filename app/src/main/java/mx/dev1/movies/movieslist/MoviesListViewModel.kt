package mx.dev1.movies.movieslist

import android.util.Log
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
import mx.dev1.movies.data.remote.RetrofitClient
import java.net.ConnectException

class MoviesListViewModel(
    private val repository: MoviesRepository
): ViewModel() {
    private val movieListUiStateFlow = MutableStateFlow(MovieUiState())
    val movieListUiState: StateFlow<MovieUiState> = movieListUiStateFlow.asStateFlow()

    init {
        getMovies()
    }

    fun getMovies() {
        viewModelScope.launch {
            try {
                movieListUiStateFlow.update {
                    it.copy(
                        isLoading = true
                    )
                }

                delay(2000L)
                val movies =  repository.getMovies()

                movieListUiStateFlow.update { moviesUiState ->
                    moviesUiState.copy(
                        movieList = movies,
                        isLoading = false,
                        errorEnum = null
                    )
                }
            } catch(e: Exception) {
                val errorEnum = when {
                    e is ConnectException -> ErrorMessage.INTERNET_CONNECTION
                    else -> ErrorMessage.DEFAULT
                }

                movieListUiStateFlow.update { moviesUiState ->
                    moviesUiState.copy(
                        isLoading = false,
                        errorEnum = errorEnum
                    )
                }
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                MoviesListViewModel (
                    MoviesRepository(
                        RetrofitClient.service
                    )
                )
            }
        }
    }
}
