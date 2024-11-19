package mx.dev1.movies.presentation.detail.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import mx.dev1.movies.models.MovieDetail

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailsTopBar(
    movieDetail: MovieDetail,
    onBack: () -> Unit,
    onUpdateFavorites: (MovieDetail) -> Unit
) {
    CenterAlignedTopAppBar(
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
            }
        },
        title = {
            Text(
                text = movieDetail.title,
                style = MaterialTheme.typography.titleMedium
            )
        },
        actions = {
            IconButton(onClick = {
                onUpdateFavorites(movieDetail)
            }) {
                Icon(imageVector = if (movieDetail.isMovieInFavorites) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder, contentDescription = null)
            }
        }
    )
}