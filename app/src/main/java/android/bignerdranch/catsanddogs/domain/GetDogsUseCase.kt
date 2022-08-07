package android.bignerdranch.catsanddogs.domain

import android.bignerdranch.catsanddogs.data.repository.AnimalRepositoryImpl

/*
class GetDogsUseCase(private val repository: AnimalRepositoryImpl
) {
    suspend operator fun invoke() = repository.getDogResponse()
}

*/

class GetDogsUseCase(private val repository: AnimalRepositoryImpl) {
    suspend fun start() = repository.getDogResponse()
}