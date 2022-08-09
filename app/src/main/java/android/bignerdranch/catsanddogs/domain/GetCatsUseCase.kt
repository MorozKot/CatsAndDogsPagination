package android.bignerdranch.catsanddogs.domain

import android.bignerdranch.catsanddogs.domain.repository.AnimalRepository
import javax.inject.Inject

class GetCatsUseCase @Inject constructor(private val repository: AnimalRepository
) {
    suspend fun start() = repository.getCatResponse()
}
