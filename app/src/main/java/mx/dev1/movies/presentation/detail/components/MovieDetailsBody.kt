package mx.dev1.movies.presentation.detail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import mx.dev1.movies.models.MovieDetail

@Composable
fun MovieDetailsBody(
    movieDetail: MovieDetail
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        MovieInfoContainer(movieDetail = movieDetail)
        MovieDescriptionContainer(description = movieDetail.overview)
    }
}