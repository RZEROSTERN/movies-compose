package mx.dev1.movies.data.remote

import com.squareup.moshi.Json
import mx.dev1.movies.models.Movie

data class MoviesResultResponse(
    val page: Int,
    @Json(name = "total_pages")
    val totalPages: Long,
    @Json(name = "totalResults")
    val totalResults: Long,
    val result: List<MoviesDetailResponse>
)