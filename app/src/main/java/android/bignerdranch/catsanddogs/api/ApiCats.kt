package android.bignerdranch.catsanddogs.api

import android.bignerdranch.catsanddogs.model.CatsResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiCats {
    @GET("facts?max_length=400&limit=10")
    suspend fun getCatsFact(): Response<CatsResponse>
}
