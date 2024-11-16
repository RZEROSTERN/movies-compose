package mx.dev1.movies.data.repository

import mx.dev1.movies.models.Movie
import mx.dev1.movies.models.toMovieModelList

interface MoviesRepository {
    suspend fun getMovies(): List<Movie>
}
