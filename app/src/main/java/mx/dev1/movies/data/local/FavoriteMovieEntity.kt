package mx.dev1.movies.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavoriteMovieEntity(
    @PrimaryKey
    @ColumnInfo(name = "movie_id")
    val movieId: String,
    @ColumnInfo(name = "poster_path")
    val posterPath: String,
)
