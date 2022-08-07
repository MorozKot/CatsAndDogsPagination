package android.bignerdranch.catsanddogs.domain.repository

import android.bignerdranch.catsanddogs.data.network.RetrofitInstance
import android.bignerdranch.catsanddogs.data.network.model.CatsResponse

class AnimalRepositoryImpl: AnimalRepository {

    private val apiDogs = RetrofitInstance.apiDogs

    private val apiCats = RetrofitInstance.apiCats

    override suspend fun getDogResponse(): GetMoviesResult {
        var result: GetMoviesResult = GetMoviesResult.EnqueueError("MoviesRepositoryImpl getMovies not working")
        try {
            val call =  apiDogs.dogsResponse()
            when{
                call.isSuccessful->{
                    call.body()?.let {
                        result = GetMoviesResult.Success(it)
                    } ?: run {
                        result = GetMoviesResult.ServerError(
                            error = call.errorBody()?.source()?.buffer?.snapshot()?.utf8()
                        )
                    }
                }
                call.code() in 400..499 ->{
                    result = GetMoviesResult.ServerError(
                        error = "Client Error: ${call.errorBody()?.source()?.buffer?.snapshot()?.utf8()}"
                    )
                }
                call.code() in 500..599 ->{
                    result = GetMoviesResult.ServerError(
                        error = "Server Error: ${call.errorBody()?.source()?.buffer?.snapshot()?.utf8()}"
                    )

                }
                else -> {
                    result = GetMoviesResult.ServerError(
                        error = "Unknown Error: ${call.errorBody()?.source()?.buffer?.snapshot()?.utf8()}"
                    )

                }
            }
        } catch (e: Exception) {
            result = GetMoviesResult.ConnectionError()
        }
        return result
    }

    override suspend fun getCatResponse(): CatsResponse {

        return apiCats.catsResponse()
    }
}