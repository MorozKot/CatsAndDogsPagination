package android.bignerdranch.catsanddogs.presentation

import android.bignerdranch.catsanddogs.domain.GetCatsUseCase
import android.bignerdranch.catsanddogs.domain.GetDogsUseCase
import android.bignerdranch.catsanddogs.domain.repository.AnimalRepositoryImpl
import android.bignerdranch.catsanddogs.domain.repository.GetMoviesResult
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class AnimalViewModel : ViewModel() {

    private val repositoryCatsDogs = AnimalRepositoryImpl()

    val getCatsUseCase = GetCatsUseCase(repositoryCatsDogs)
    val getDogsUseCase = GetDogsUseCase(repositoryCatsDogs)

    private val _getMoviesResponse: MutableLiveData<MoviesStateVM?> = MutableLiveData(null)
    val getMoviesResponse: LiveData<MoviesStateVM?> = _getMoviesResponse

    val movieList = mutableListOf<String>()

    fun getMovies(isAdditionalLoading: Boolean = false) {
        viewModelScope.launch {
            when (val result = getDogsUseCase.start()) {
                is GetMoviesResult.Success -> {
                    result.response.message.forEach {
                        if (!movieList.contains(it)) movieList.add(it)
                    }
                    when (isAdditionalLoading) {
                        true -> _getMoviesResponse.value =
                            MoviesStateVM.MoreMovies(result.response.message)
                        false -> _getMoviesResponse.value = MoviesStateVM.GotMovies(movieList)
                    }
                }
                else -> {
                    if (movieList.isEmpty()) _getMoviesResponse.value =
                        MoviesStateVM.Error(result, result.error)
                    else {
                        _getMoviesResponse.value = MoviesStateVM.MoreMoviesError
                    }
                }
            }
        }
    }

    fun loadMore() {
        getMovies(true)
    }
}