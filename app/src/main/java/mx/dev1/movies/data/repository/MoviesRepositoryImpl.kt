package mx.dev1.movies.data.repository

import mx.dev1.movies.data.remote.MovieDbApi
import mx.dev1.movies.models.Movie
import mx.dev1.movies.models.toMovieModelList
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val movieDbApi: MovieDbApi
) : MoviesRepository {
    override suspend fun getMovies(): List<Movie> = movieDbApi.getPopularMovies().toMovieModelList()
}