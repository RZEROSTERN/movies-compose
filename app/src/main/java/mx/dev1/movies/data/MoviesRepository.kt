package mx.dev1.movies.data

import mx.dev1.movies.data.remote.MovieDbApi
import mx.dev1.movies.models.Movie
import mx.dev1.movies.models.toMovieModelList

class MoviesRepository(
    private val movieDbApi: MovieDbApi
) {
    suspend fun getMovies(): List<Movie> = movieDbApi.getPopularMovies().toMovieModelList()
}
