package android.bignerdranch.catsanddogs.presentation.viewmodel

import android.bignerdranch.catsanddogs.domain.GetCatsUseCase
import android.bignerdranch.catsanddogs.domain.GetDogsUseCase
import android.bignerdranch.catsanddogs.domain.repository.GetDogsResult
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import javax.inject.Inject


class AnimalViewModel @Inject constructor(
    private val getCatsUseCase: GetCatsUseCase,
    private val getDogsUseCase: GetDogsUseCase
) : ViewModel() {

/*
    private val repositoryCatsDogs = AnimalRepositoryImpl()
*/

    private val _getDogsResponse: MutableLiveData<DogsStateVM?> = MutableLiveData(null)
    val getDogsResponse: LiveData<DogsStateVM?> = _getDogsResponse

    val dogsList = mutableListOf<String>()

    fun getDogs(isAdditionalLoading: Boolean = false) {
        viewModelScope.launch {
            when (val result = getDogsUseCase.start()) {
                is GetDogsResult.Success -> {
                    result.response.message.forEach {
                        if (!dogsList.contains(it)) dogsList.add(it)
                    }
                    when (isAdditionalLoading) {
                        true -> _getDogsResponse.value =
                            DogsStateVM.MoreDogs(result.response.message)
                        false -> _getDogsResponse.value = DogsStateVM.GotDogs(dogsList)
                    }
                }
                else -> {
                    if (dogsList.isEmpty()) _getDogsResponse.value =
                        DogsStateVM.Error(result, result.error)
                    else {
                        _getDogsResponse.value = DogsStateVM.MoreDogsError
                    }
                }
            }
        }
    }

    fun loadMore() {
        getDogs(true)
    }
}