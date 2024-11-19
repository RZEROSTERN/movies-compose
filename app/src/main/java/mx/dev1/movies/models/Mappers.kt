package mx.dev1.movies.models

import mx.dev1.movies.data.local.FavoriteMovieEntity
import mx.dev1.movies.data.remote.MoviesResultResponse
import mx.dev1.movies.data.remote.detail.MoviesDetailResponse

const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/original"
fun MoviesResultResponse.toMovieModelList() : List<Movie> = this.results.map { moviesDetailResponse ->
        Movie(
            id = moviesDetailResponse.id,
            title = moviesDetailResponse.title,
            imageUrl = "$BASE_IMAGE_URL${moviesDetailResponse.posterPath}",
            isFavorite = false
        )
    }

fun MoviesDetailResponse.toMovieDetail() : MovieDetail = MovieDetail (
    id = this.id,
    title = this.title,
    posterPath = "$BASE_IMAGE_URL${this.posterPath}",
    overview = this.overview,
    releaseDate = this.releaseDate,
    genres = this.genres,
    popularity = this.popularity,
    tagline = this.tagline,
    voteAverage = this.voteAverage,
    video = this.video,
    backdropPath = "$BASE_IMAGE_URL${this.backdropPath}",
    runtime = this.runtime
)

fun FavoriteMovieEntity.transformToMovieModel() : Movie = Movie(
    id = this.movieId.toInt(),
    imageUrl = this.posterPath
)

fun Movie.transformToMovieEntity() : FavoriteMovieEntity = FavoriteMovieEntity(
    movieId = this.id.toString(),
    posterPath = this.imageUrl
)
