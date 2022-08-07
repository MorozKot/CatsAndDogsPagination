package android.bignerdranch.catsanddogs.domain

import android.bignerdranch.catsanddogs.domain.repository.AnimalRepository

/*
class GetDogsUseCase(private val repository: AnimalRepositoryImpl
) {
    suspend operator fun invoke() = repository.getDogResponse()
}

*/

class GetDogsUseCase(private val repository: AnimalRepository) {
    suspend fun start() = repository.getDogResponse()
}