package mx.dev1.movies.presentation.home

import androidx.annotation.StringRes
import mx.dev1.movies.R

enum class ErrorMessage(@StringRes val message: Int) {
    INTERNET_CONNECTION(R.string.network_error),
    DEFAULT(R.string.default_error)
}