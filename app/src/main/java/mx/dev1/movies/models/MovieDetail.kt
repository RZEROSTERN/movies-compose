package mx.dev1.movies.models

import mx.dev1.movies.data.remote.detail.Genre

data class MovieDetail(
    val adult: Boolean,
    val backdropPath: String,
    val genres: List<Genre>,
    val id: Int,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Float,
    val posterPath: String,
    val releaseDate: String,
    val revenue: Long,
    val runtime: Int,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val voteAverage: String,
    val voteCount: String
)
