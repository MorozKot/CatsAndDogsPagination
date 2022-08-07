package android.bignerdranch.catsanddogs.domain.repository

import android.bignerdranch.catsanddogs.data.network.model.CatsResponse

interface AnimalRepository {

    suspend fun getDogResponse(): GetMoviesResult

    suspend fun getCatResponse(): CatsResponse
}
