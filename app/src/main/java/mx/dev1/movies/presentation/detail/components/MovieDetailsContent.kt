package mx.dev1.movies.presentation.detail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import mx.dev1.movies.models.MovieDetail

@Composable
fun MovieDetailsContent(
    movieDetail: MovieDetail
) {
    Scaffold(
        topBar = {
            MovieDetailsTopBar(movieDetail = movieDetail)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            MovieDetailsHeader(
                movieDetail = movieDetail,
                modifier = Modifier.fillMaxWidth().height(400.dp)
            )
            MovieDetailsBody(movieDetail = movieDetail)
        }
    }
}
