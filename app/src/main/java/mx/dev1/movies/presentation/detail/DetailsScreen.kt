package mx.dev1.movies.presentation.detail

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import mx.dev1.movies.presentation.detail.components.MovieDetailsContent
import mx.dev1.movies.presentation.home.MoviesListViewModel

@Composable
fun DetailsScreen(
    movieId: String?,
    viewModel: MovieDetailsViewModel
) {
    LaunchedEffect(movieId) {
        viewModel.getMovieDetails(movieId.orEmpty())
    }

    val movieDetailsUiState by viewModel.movieDetailUiState.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        LaunchedEffect(movieDetailsUiState.errorEnum) {
            if(movieDetailsUiState.errorEnum != null) {
                Log.d("DEBUG", "DetailsScreen: ${movieDetailsUiState.errorEnum?.message}")
            }
        }

        if(movieDetailsUiState.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            movieDetailsUiState.movieDetail?.let {
                MovieDetailsContent(movieDetail = it)
            }
        }
    }


}

@Preview(showBackground = true)
@Composable
fun DetailsScreenPreview() {
    DetailsScreen(
        movieId = "1",
        viewModel = hiltViewModel<MovieDetailsViewModel>()
    )
}
