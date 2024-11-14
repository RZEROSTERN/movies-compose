package mx.dev1.movies.movieslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import mx.dev1.movies.data.MovieUiState
import mx.dev1.movies.models.Movie

class MoviesListViewModel: ViewModel() {
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

            val movies = mockMovieList // Change to repository implementation

            movieListUiStateFlow.update { moviesUiState ->
                moviesUiState.copy(
                    movieList = movies,
                    isLoading = false
                )
            }
        }
    }
}



val mockMovieList = listOf(
    Movie(0, "Oppenheimer", "https://static1.srcdn.com/wordpress/wp-content/uploads/2023/05/oppenheimer-poster.jpg", false),
    Movie(1, "Inside Out 2", "https://ik.imagekit.io/9ifn2ouyo26/movies/inside-out-2/inside-out-2-poster.jpg", false),
    Movie(2, "Gladiator II", "https://static1.srcdn.com/wordpress/wp-content/uploads/2024/07/gladiator-2-2024-new-film-poster.jpg", false),
    Movie(3, "Terrifier III", "https://static1.srcdn.com/wordpress/wp-content/uploads/2023/11/terrifier-3-poster.jpg", false),
)
