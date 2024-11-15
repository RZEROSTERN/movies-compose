package mx.dev1.movies.movieslist

import android.graphics.Paint.Align
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun MoviesListScreen(
    viewModel: MoviesListViewModel = viewModel(
        factory = MoviesListViewModel.Factory
    )
) {
    val movieUiState by viewModel.movieListUiState.collectAsState()

    if(movieUiState.isLoading) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator()
        }
    }

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        verticalItemSpacing = 8.dp,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        content = {
            items(movieUiState.movieList) { movie ->
                var isFavorite by rememberSaveable {
                    mutableStateOf(false)
                }
                MovieCard(
                    movie = movie,
                    isFavorite = isFavorite,
                    onFavoriteClick = {
                        isFavorite = !isFavorite
                    }
                )
            }
        },
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 2.dp, vertical = 4.dp)
    )

    if(movieUiState.errorEnum != null) {
        MovieError(
            errorMessage = stringResource(id = movieUiState.errorEnum?.message ?: -1)
        ) {
            viewModel.getMovies()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MoviesListScreenPreview() {
    MoviesListScreen()
}
