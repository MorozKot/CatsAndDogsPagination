package android.bignerdranch.catsanddogs.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val retrofitDogs = Retrofit.Builder()
        .baseUrl("https://dog.ceo/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val retrofitCats = Retrofit.Builder()
        .baseUrl("https://catfact.ninja/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiDogs: ApiDogs by lazy {
        retrofitDogs.create(ApiDogs::class.java)
    }

    val apiCats: ApiCats by lazy {
        retrofitCats.create(ApiCats::class.java)
    }
}