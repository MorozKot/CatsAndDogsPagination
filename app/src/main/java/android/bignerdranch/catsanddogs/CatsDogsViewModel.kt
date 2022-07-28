package android.bignerdranch.catsanddogs

import android.bignerdranch.catsanddogs.model.CatsResponse
import android.bignerdranch.catsanddogs.model.DogsResponse
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class CatsDogsViewModel : ViewModel() {

    var catsDogsRepository = CatsDogsRepository()

    val dogsList: MutableLiveData<DogsResponse> =
        MutableLiveData()

    val catsList: MutableLiveData<CatsResponse> =
        MutableLiveData()

    fun load() {
        viewModelScope.launch {
            dogsList.value = catsDogsRepository.getDogResponse()
            catsList.value = catsDogsRepository.getCatResponse()
        }
    }
}