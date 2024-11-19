@file:OptIn(ExperimentalMaterial3Api::class)

package mx.dev1.movies.presentation.favorites

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import mx.dev1.movies.models.Movie
import mx.dev1.movies.presentation.home.MovieCard

@Composable
fun FavoriteMoviesScreen(
    viewModel: FavoriteMoviesViewModel = hiltViewModel(),
    navigateToDetails: (Movie) -> Unit
) {
    val favoriteMoviesUiState by viewModel.favoriteUiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(favoriteMoviesUiState.errorEnum) {
        favoriteMoviesUiState.errorEnum?.let {
            Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        if(favoriteMoviesUiState.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            val movieList = favoriteMoviesUiState.movieList

            if(movieList.isNotEmpty()) {
                LazyVerticalStaggeredGrid(
                    verticalItemSpacing = 8.dp,
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
                    columns = StaggeredGridCells.Fixed(2),
                    content = {
                        items(movieList, key = { movie ->
                            movie.id
                        }) { movie ->
                            val dismissState = rememberSwipeToDismissBoxState(
                                confirmValueChange = { dismissValue ->
                                    if(dismissValue == SwipeToDismissBoxValue.StartToEnd) {
                                        viewModel.deleteMovieFromFavorites(movie)
                                        Toast.makeText(context, "Película eliminada", Toast.LENGTH_SHORT).show()
                                        true
                                    } else {
                                        false
                                    }
                                }
                            )

                            /**
                             * DEPRECATED
                             * We should change it for new implementation in Material 3
                             */
                            SwipeToDismiss(
                                state = dismissState,
                                background = {},
                                dismissContent = {
                                    MovieCard(
                                        movie = movie,
                                        onMovieClick = { movieClicked ->
                                            navigateToDetails(movieClicked)
                                        }
                                    )
                                }
                            )
                        }
                    }
                )
            } else {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = "No tienes ninguna película",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}