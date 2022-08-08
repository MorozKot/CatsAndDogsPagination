package android.bignerdranch.catsanddogs.domain

import android.bignerdranch.catsanddogs.data.repository.AnimalRepositoryImpl

class GetCatsUseCase(private val repository: AnimalRepositoryImpl
) {
    suspend fun start() = repository.getCatResponse()
}
