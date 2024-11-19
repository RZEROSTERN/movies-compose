package mx.dev1.movies.data.repository

import mx.dev1.movies.data.remote.detail.MoviesDetailResponse
import mx.dev1.movies.models.Movie
import mx.dev1.movies.models.MovieDetail
import mx.dev1.movies.models.toMovieModelList

interface MoviesRepository {
    suspend fun getMovies(): List<Movie>

    suspend fun getMovieDetail(movieId: String): MovieDetail
}
