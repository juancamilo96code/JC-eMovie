package com.jc.collantes.emovie.presentation.ui.screens.home

import androidx.lifecycle.*
import com.jc.collantes.emovie.data.model.Resource
import com.jc.collantes.emovie.data.model.ui.CustomChipData
import com.jc.collantes.emovie.data.model.ui.SimpleMovieItem
import com.jc.collantes.emovie.domain.usescases.*
import com.jc.collantes.emovie.utils.FILTER_LANGUAGE
import com.jc.collantes.emovie.utils.FILTER_YEAR
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeMoviesCatalogViewModel @Inject constructor(
    private val getUpComingMoviesUseCase: GetUpComingMoviesUseCase,
    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase,
    private val getRecommendedMoviesFilteredUseCase: GetRecommendedMoviesFilteredUseCase
) : ViewModel() {

    private val _upcomingList = MutableLiveData<Resource<List<SimpleMovieItem>>>()
    val upcomingList: LiveData<Resource<List<SimpleMovieItem>>> = _upcomingList

    private val _topRatedList = MutableLiveData<Resource<List<SimpleMovieItem>>>()
    val topRatedList: LiveData<Resource<List<SimpleMovieItem>>> = _topRatedList

    private val customChipDataList = listOf(
        CustomChipData(FILTER_LANGUAGE, "En Español", "es", false) {},
        CustomChipData(FILTER_YEAR, "Lanzadas en 1994", "1994", false) {},
    )

    private val _filterList = MutableLiveData(customChipDataList)
    val filterList: LiveData<List<CustomChipData>> = _filterList

    private val _recommendationList = MutableLiveData<Resource<List<SimpleMovieItem>>>()
    val recommendationList: LiveData<Resource<List<SimpleMovieItem>>> = _recommendationList

    private val _emptyState = MutableLiveData(false)
    val emptyState: LiveData<Boolean> = _emptyState

    init {
        loadData()
    }

    fun loadData(){
        getUpcomingList()
        getTopRatedList()
        getRecommendedList(null, null)
    }

    private fun getUpcomingList() {
        viewModelScope.launch {
            _upcomingList.value = Resource.Loading()
            _upcomingList.value = getUpComingMoviesUseCase()
            validateEmpty()
        }
    }

    private fun getTopRatedList() {
        viewModelScope.launch {
            _topRatedList.value = Resource.Loading()
            _topRatedList.value = getTopRatedMoviesUseCase()
            validateEmpty()
        }
    }

    private fun getRecommendedList(filterYear: String?, filterLanguage: String?) {
        viewModelScope.launch {
            _recommendationList.value = Resource.Loading()
            _recommendationList.value = getRecommendedMoviesFilteredUseCase(filterYear,filterLanguage)
            validateEmpty()
        }
    }

    fun filterRecommendedList() {
        getRecommendedList(
            getFilterValue(FILTER_YEAR),
            getFilterValue(FILTER_LANGUAGE)
        )
    }

    private fun getFilterValue(key: String): String? {
        val filterList = filterList.value?.filter { (it.id == key && it.isActive) }
        return if (filterList.isNullOrEmpty()) {
            null
        } else filterList[0].internalValue
    }

    private fun validateEmpty(){
        _emptyState.value = (upcomingList.value is Resource.GenericDataError
                && topRatedList.value is Resource.GenericDataError
                && recommendationList.value is Resource.GenericDataError)
    }
}
