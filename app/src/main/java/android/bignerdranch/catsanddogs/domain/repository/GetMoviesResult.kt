package android.bignerdranch.catsanddogs.domain.repository

import android.bignerdranch.catsanddogs.data.network.model.DogsResponse


sealed class GetMoviesResult(open val error: String? = null) {
    data class Success(val response: DogsResponse) : GetMoviesResult()
    data class ConnectionError(override val error: String? = null) : GetMoviesResult(error = error)
    data class ServerError(override val error: String? = null) : GetMoviesResult(error = error)
    data class EnqueueError(override val error: String? = null) : GetMoviesResult(error = error)
}
