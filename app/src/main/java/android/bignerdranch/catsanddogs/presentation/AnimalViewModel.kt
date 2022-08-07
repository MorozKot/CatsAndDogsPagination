package android.bignerdranch.catsanddogs.presentation

import android.app.Application
import android.bignerdranch.catsanddogs.data.network.model.CatsFactDto
import android.bignerdranch.catsanddogs.data.network.model.CatsResponse
import android.bignerdranch.catsanddogs.data.network.model.DogsResponse
import android.bignerdranch.catsanddogs.data.repository.AnimalRepositoryImpl
import android.bignerdranch.catsanddogs.domain.GetCatsUseCase
import android.bignerdranch.catsanddogs.domain.GetDogsUseCase
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class AnimalViewModel(application: Application) : AndroidViewModel(application) {

    private val repositoryCatsDogs = AnimalRepositoryImpl(application)

    val getCatsUseCase = GetCatsUseCase(repositoryCatsDogs)
    val getDogsUseCase = GetDogsUseCase(repositoryCatsDogs)


/*    fun getCatModel() {
        viewModelScope.launch {
            catsList.value = getCatsUseCase.invoke()
        }
    }*/

    val catsList: MutableLiveData<CatsFactDto> =
        MutableLiveData()

    val dogList: MutableLiveData<DogsResponse> =
        MutableLiveData()

    private var _shopList =  MutableLiveData<String>()
    val shopList: LiveData<String>
        get() = _shopList

    val dogL = mutableListOf<String>()

    fun getDogs() {
        viewModelScope.launch {
            getDogsUseCase.start().message.forEach {
                if (!dogL.contains(it)) dogL.add(it)

                _shopList.value = dogL.toString()

                Log.d("getDogs AnimalViewModel", "$dogL")
                Log.d("getDogs AnimalViewModel _shopList", "$shopList")
            }
        }
    }

/*    val catL = mutableListOf<CatsFactDto>()

    fun getCats() {
        viewModelScope.launch {
            getCatsUseCase.start().data.forEach {
                if (!catL.contains(it)) catL.add(it)

                Log.d("getDogs AnimalViewModel", "$catL")
            }
        }
    }*/
}