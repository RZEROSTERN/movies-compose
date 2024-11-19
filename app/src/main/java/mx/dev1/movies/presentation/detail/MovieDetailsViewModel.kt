package mx.dev1.movies.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import mx.dev1.movies.data.MovieDetailUiState
import mx.dev1.movies.data.repository.MoviesRepository
import mx.dev1.movies.presentation.home.ErrorMessage
import java.net.ConnectException
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val repository: MoviesRepository
) : ViewModel() {
    private val movieDetailUiStateFlow = MutableStateFlow(MovieDetailUiState())
    val movieDetailUiState: StateFlow<MovieDetailUiState> = movieDetailUiStateFlow.asStateFlow()

    fun getMovieDetails(movieId: String) {
        viewModelScope.launch {
            try {
                movieDetailUiStateFlow.update {
                    it.copy(
                        isLoading = true
                    )
                }

                val movieDetail = repository.getMovieDetail(movieId)

                movieDetailUiStateFlow.update { movieDetailUiState ->
                    movieDetailUiState.copy(
                        movieDetail = movieDetail,
                        isLoading = false,
                        errorEnum = null
                    )
                }
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
}
