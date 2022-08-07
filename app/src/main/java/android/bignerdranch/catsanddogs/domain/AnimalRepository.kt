package android.bignerdranch.catsanddogs.domain

import android.bignerdranch.catsanddogs.data.network.model.CatsFactDto
import android.bignerdranch.catsanddogs.data.network.model.CatsResponse
import android.bignerdranch.catsanddogs.data.network.model.DogsResponse
import androidx.lifecycle.LiveData
import retrofit2.Response

interface AnimalRepository {

    suspend fun getDogResponse(): DogsResponse

    suspend fun getCatResponse(): CatsResponse
}
