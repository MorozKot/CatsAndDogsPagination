package android.bignerdranch.catsanddogs.domain.repository

import android.bignerdranch.catsanddogs.data.network.RetrofitInstance
import android.bignerdranch.catsanddogs.data.network.model.CatsResponse

class AnimalRepositoryImpl : AnimalRepository {

    private val apiDogs = RetrofitInstance.apiDogs

    private val apiCats = RetrofitInstance.apiCats

    override suspend fun getDogResponse(): GetDogsResult {
        var result: GetDogsResult =
            GetDogsResult.EnqueueError("AnimalRepositoryImpl getDogResponse not working")
        try {
            val call = apiDogs.dogsResponse()
            when {
                call.isSuccessful -> {
                    call.body()?.let {
                        result = GetDogsResult.Success(it)
                    } ?: run {
                        result = GetDogsResult.ServerError(
                            error = call.errorBody()?.source()?.buffer?.snapshot()?.utf8()
                        )
                    }
                }
                call.code() in 400..499 -> {
                    result = GetDogsResult.ServerError(
                        error = "Client Error: ${
                            call.errorBody()?.source()?.buffer?.snapshot()?.utf8()
                        }"
                    )
                }
                call.code() in 500..599 -> {
                    result = GetDogsResult.ServerError(
                        error = "Server Error: ${
                            call.errorBody()?.source()?.buffer?.snapshot()?.utf8()
                        }"
                    )
                }
                else -> {
                    result = GetDogsResult.ServerError(
                        error = "Unknown Error: ${
                            call.errorBody()?.source()?.buffer?.snapshot()?.utf8()
                        }"
                    )
                }
            }
        } catch (e: Exception) {
            result = GetDogsResult.ConnectionError()
        }
        return result
    }

    override suspend fun getCatResponse(): CatsResponse {
        return apiCats.catsResponse()
    }
}