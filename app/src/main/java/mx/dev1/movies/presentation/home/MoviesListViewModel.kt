package mx.dev1.movies.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import mx.dev1.movies.data.repository.MoviesRepository
import java.net.ConnectException
import javax.inject.Inject

@HiltViewModel
class MoviesListViewModel @Inject constructor(
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
}
