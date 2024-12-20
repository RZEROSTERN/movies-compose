package mx.dev1.movies.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        FavoriteMovieEntity::class
    ],
    version = 1
)
abstract class FavoriteMovieDatabase : RoomDatabase() {
    abstract fun createFavoriteMovieDao() : FavoriteMovieDao
}
