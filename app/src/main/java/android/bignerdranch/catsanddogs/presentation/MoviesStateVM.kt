package android.bignerdranch.catsanddogs.presentation

import android.bignerdranch.catsanddogs.domain.repository.GetMoviesResult

sealed class MoviesStateVM {
    data class GotMovies(val list: List<String>): MoviesStateVM()
    data class MoreMovies(val more: List<String>?): MoviesStateVM()
    object MoreMoviesError: MoviesStateVM()
    data class Error(val result: GetMoviesResult, val msg:String?): MoviesStateVM()
}