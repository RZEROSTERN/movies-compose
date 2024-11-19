package mx.dev1.movies.presentation.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import mx.dev1.movies.data.repository.MoviesRepository
import mx.dev1.movies.presentation.home.ErrorMessage
import java.net.ConnectException
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

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

            delay(1.seconds)

            try {
                val movieDetail = repository.getMovieDetail(movieId = movieId)

                movieDetailUiStateFlow.update {
                    it.copy(
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
