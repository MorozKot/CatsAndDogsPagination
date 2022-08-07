package android.bignerdranch.catsanddogs.data.repository

import android.app.Application
import android.bignerdranch.catsanddogs.data.network.RetrofitInstance
import android.bignerdranch.catsanddogs.data.network.model.CatsResponse
import android.bignerdranch.catsanddogs.data.network.model.DogsResponse
import android.bignerdranch.catsanddogs.domain.AnimalRepository

class AnimalRepositoryImpl(private val application: Application): AnimalRepository {

    private val apiDogs = RetrofitInstance.apiDogs

    private val apiCats = RetrofitInstance.apiCats

    override suspend fun getDogResponse(): DogsResponse {

        return apiDogs.dogsResponse()
    }

    override suspend fun getCatResponse(): CatsResponse {

        return apiCats.catsResponse()
    }
}