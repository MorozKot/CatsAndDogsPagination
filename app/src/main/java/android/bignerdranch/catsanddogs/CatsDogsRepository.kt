package android.bignerdranch.catsanddogs

import android.bignerdranch.catsanddogs.api.RetrofitInstance
import android.bignerdranch.catsanddogs.model.CatsResponse
import android.bignerdranch.catsanddogs.model.DogsResponse

class CatsDogsRepository {

    suspend fun getDogResponse(): DogsResponse {
        return RetrofitInstance.apiDogs.getDogsList().body() ?: DogsResponse(emptyList())
    }

    suspend fun getCatResponse(): CatsResponse {
        return RetrofitInstance.apiCats.getCatsFact().body() ?: CatsResponse(emptyList())
    }
}