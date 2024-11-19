package mx.dev1.movies.data.remote

import dagger.Provides
import mx.dev1.movies.BuildConfig
import mx.dev1.movies.data.remote.detail.MoviesDetailResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieDbApi {
    @GET("3/movie/popular")
    suspend fun getPopularMovies(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): MoviesResultResponse

    @GET("3/movie/{movieId}")
    suspend fun getMovieDetails(
        @Query("movie_id") movieId: String,
        @Query("language") language: String = "en-US",
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): MoviesDetailResponse
}