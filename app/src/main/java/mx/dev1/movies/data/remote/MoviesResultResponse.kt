package mx.dev1.movies.data.remote

import com.squareup.moshi.Json
import mx.dev1.movies.data.remote.detail.MoviesDetailResponse

data class MoviesResultResponse(
    val page: Int,
    @Json(name = "total_pages")
    val totalPages: Long,
    @Json(name = "total_results")
    val totalResults: Long,
    val results: List<MoviesDetailResponse>
)