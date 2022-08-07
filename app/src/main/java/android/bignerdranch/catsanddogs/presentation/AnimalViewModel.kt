package android.bignerdranch.catsanddogs.presentation

import android.app.Application
import android.bignerdranch.catsanddogs.data.network.model.CatsResponse
import android.bignerdranch.catsanddogs.data.network.model.DogsResponse
import android.bignerdranch.catsanddogs.data.repository.AnimalRepositoryImpl
import android.bignerdranch.catsanddogs.domain.GetCatsUseCase
import android.bignerdranch.catsanddogs.domain.GetDogsUseCase
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class AnimalViewModel(application: Application) : AndroidViewModel(application) {

    private val repositoryCatsDogs = AnimalRepositoryImpl(application)

    val getCatsUseCase = GetCatsUseCase(repositoryCatsDogs)
    val getDogsUseCase = GetDogsUseCase(repositoryCatsDogs)

    val catsList: MutableLiveData<CatsResponse> =
        MutableLiveData()

    fun getCatModel() {
        viewModelScope.launch {
            catsList.value = getCatsUseCase.invoke()
        }
    }

    val dogList: MutableLiveData<DogsResponse> =
        MutableLiveData()

    fun getDogModel() {
        viewModelScope.launch {
            dogList.value = getDogsUseCase.invoke()
        }
    }

    fun loadMore() {
        getCatModel()
        getDogModel()
    }
}
