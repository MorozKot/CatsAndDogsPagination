package android.bignerdranch.catsanddogs.api

import android.bignerdranch.catsanddogs.model.DogsResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiDogs {
    @GET("api/breeds/image/random/10")
    suspend fun getDogsList(): Response<DogsResponse>
}
