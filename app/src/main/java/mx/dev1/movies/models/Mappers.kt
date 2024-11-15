package mx.dev1.movies.models

import mx.dev1.movies.data.remote.MoviesResultResponse

const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/original"
fun MoviesResultResponse.toMovieModelList() : List<Movie> = this.results.map { moviesDetailResponse ->
        Movie(
            id = moviesDetailResponse.id,
            title = moviesDetailResponse.title,
            imageUrl = "$BASE_IMAGE_URL${moviesDetailResponse.posterPath}",
            isFavorite = false
        )
    }