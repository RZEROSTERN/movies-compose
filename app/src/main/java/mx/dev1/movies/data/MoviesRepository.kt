package mx.dev1.movies.data

import mx.dev1.movies.models.Movie

class MoviesRepository {
    suspend fun getMovies(): List<Movie> = mockMovieList
}

val mockMovieList: List<Movie> = listOf(
    Movie(0, "Oppenheimer", "https://static1.srcdn.com/wordpress/wp-content/uploads/2023/05/oppenheimer-poster.jpg", false),
    Movie(1, "Inside Out 2", "https://ik.imagekit.io/9ifn2ouyo26/movies/inside-out-2/inside-out-2-poster.jpg", false),
    Movie(2, "Gladiator II", "https://static1.srcdn.com/wordpress/wp-content/uploads/2024/07/gladiator-2-2024-new-film-poster.jpg", false),
    Movie(3, "Terrifier III", "https://static1.srcdn.com/wordpress/wp-content/uploads/2023/11/terrifier-3-poster.jpg", false),
)