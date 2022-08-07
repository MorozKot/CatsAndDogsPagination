package android.bignerdranch.catsanddogs.data.network

import android.bignerdranch.catsanddogs.data.network.model.CatsResponse
import androidx.lifecycle.LiveData
import retrofit2.Response
import retrofit2.http.GET

interface ApiCats {
    @GET("facts?max_length=400&limit=10")
    suspend fun catsResponse(): CatsResponse
}
