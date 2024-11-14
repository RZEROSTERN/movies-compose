package mx.dev1.movies.models

data class Movie(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val isFavorite: Boolean
)
