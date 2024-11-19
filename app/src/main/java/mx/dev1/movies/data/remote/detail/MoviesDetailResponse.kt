package mx.dev1.movies.data.remote.detail

import com.squareup.moshi.Json

data class MoviesDetailResponse(
    val adult: Boolean,
    @Json(name = "backdrop_path")
    val backdropPath: String,
    val genres: List<Genre> = emptyList(),
    val id: Int,
    @Json(name = "original_language")
    val originalLanguage: String,
    @Json(name = "original_title")
    val originalTitle: String,
    val overview: String,
    val popularity: Float,
    @Json(name = "poster_path")
    val posterPath: String,
    @Json(name = "release_date")
    val releaseDate: String,
    val revenue: Long = 0L,
    val runtime: Int = 0,
    val tagline: String = "",
    val title: String,
    val video: Boolean,
    @Json(name = "vote_average")
    val voteAverage: String,
    @Json(name = "vote_count")
    val voteCount: String
)
