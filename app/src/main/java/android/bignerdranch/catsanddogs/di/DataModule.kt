package android.bignerdranch.catsanddogs.di

import android.bignerdranch.catsanddogs.data.repository.AnimalRepositoryImpl
import android.bignerdranch.catsanddogs.domain.repository.AnimalRepository
import dagger.Binds
import dagger.Module

@Module
interface DataModule {

    @Binds
    fun bindAnimalRepository(impl: AnimalRepositoryImpl): AnimalRepository
}