package mx.dev1.movies.data.remote

import com.squareup.moshi.Json
import mx.dev1.movies.data.remote.detail.MoviesDetailResponse

data class MoviesResultResponse(
    val page: Int,
    @Json(name = "total_pages")
    val totalPages: Long,
    @Json(name = "total_results")
    val totalResults: Long,
    val results: List<MovieDetailResponse>
)

data class MovieDetailResponse(
    @Json(name = "id") val id: Int,
    @Json(name = "title") val title: String,
    @Json(name = "vote_average") val voteAverage: Float,
    @Json(name = "overview") val overview: String,
    @Json(name = "poster_path") val posterPath: String,
)
