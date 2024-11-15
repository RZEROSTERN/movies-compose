package mx.dev1.movies.movieslist

enum class ErrorMessage(val message: String) {
    INTERNET_CONNECTION("Ocurrió un error, verifica tu conexión"),
    DEFAULT("Ocurrió un error desconocido")
}