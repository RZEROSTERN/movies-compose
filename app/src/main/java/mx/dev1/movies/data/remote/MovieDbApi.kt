package mx.dev1.movies.data.remote

import mx.dev1.movies.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieDbApi {
    @GET("3/movie/popular")
    suspend fun getPopularMovies(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): MoviesResultResponse
}