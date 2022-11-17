package com.jc.collantes.emovie.presentation.ui.screens.detail

import androidx.lifecycle.*
import com.jc.collantes.emovie.data.model.Resource
import com.jc.collantes.emovie.data.model.ui.MovieDetails
import com.jc.collantes.emovie.data.model.ui.MovieVideo
import com.jc.collantes.emovie.domain.usescases.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val getMovieTrailerUseCase: GetMovieTrailerUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _movieDetailsResource = MutableLiveData<Resource<MovieDetails?>>()
    val movieDetailsResource: LiveData<Resource<MovieDetails?>> = _movieDetailsResource

    private val _movieTrailer = MutableLiveData<Resource<MovieVideo?>>()
    val movieTrailer: LiveData<Resource<MovieVideo?>> = _movieTrailer

    val id = savedStateHandle.get<String>("movieId")?.toLong()

    init {
        loadData()
    }

    fun loadData (){
        id?.let {
            getMovieDetails(it)
            getMovieTrailer(it)
        }
    }

    private fun getMovieDetails(id: Long) {
        viewModelScope.launch {
            _movieDetailsResource.value = Resource.Loading()
            _movieDetailsResource.value = getMovieDetailsUseCase(id)
        }
    }

    private fun getMovieTrailer(movieId: Long) {
        viewModelScope.launch {
            _movieTrailer.value = Resource.Loading()
            _movieTrailer.value = getMovieTrailerUseCase(movieId)
        }
    }

}