package android.bignerdranch.catsanddogs.data.repository

import android.bignerdranch.catsanddogs.data.network.ApiCats
import android.bignerdranch.catsanddogs.data.network.ApiDogs
import android.bignerdranch.catsanddogs.data.network.model.CatsResponse
import android.bignerdranch.catsanddogs.domain.repository.AnimalRepository
import android.bignerdranch.catsanddogs.domain.repository.GetDogsResult
import javax.inject.Inject

class AnimalRepositoryImpl @Inject constructor(
    private val apiDogs: ApiDogs,
    private val apiCats: ApiCats
) : AnimalRepository {


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