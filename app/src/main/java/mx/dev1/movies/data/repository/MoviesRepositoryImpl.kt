package mx.dev1.movies.data.repository

import kotlinx.coroutines.flow.Flow
import mx.dev1.movies.data.local.FavoriteMovieDao
import mx.dev1.movies.data.local.FavoriteMovieEntity
import mx.dev1.movies.data.remote.MovieDbApi
import mx.dev1.movies.models.Movie
import mx.dev1.movies.models.MovieDetail
import mx.dev1.movies.models.toMovieDetail
import mx.dev1.movies.models.toMovieModelList
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val movieDbApi: MovieDbApi,
    private val favoriteMovieDao: FavoriteMovieDao
) : MoviesRepository {
    override suspend fun getMovies(): List<Movie> = movieDbApi.getPopularMovies().toMovieModelList()

    override suspend fun getMovieDetail(movieId: String): MovieDetail = movieDbApi.getMovieDetails(movieId).toMovieDetail()

    override suspend fun insertMovie(movieEntity: FavoriteMovieEntity) = favoriteMovieDao.insertMovie(movieEntity)

    override suspend fun deleteMovie(movieEntity: FavoriteMovieEntity) = favoriteMovieDao.deleteMovie(movieEntity)

    override fun getFavoriteMovies(): Flow<List<FavoriteMovieEntity>> = favoriteMovieDao.getFavoriteMovies()
}