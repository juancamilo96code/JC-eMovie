package com.jc.collantes.emovie.presentation.ui.screens.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.jc.collantes.emovie.data.model.Resource
import com.jc.collantes.emovie.data.model.ui.MovieDetails
import com.jc.collantes.emovie.data.model.ui.MovieVideo
import com.jc.collantes.emovie.data.model.ui.SimpleMovieItem
import com.jc.collantes.emovie.domain.usescases.GetMovieDetailsUseCase
import com.jc.collantes.emovie.domain.usescases.GetMovieTrailerUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MovieDetailsViewModelTest {

    @RelaxedMockK
    lateinit var getMovieDetailsUseCase: GetMovieDetailsUseCase

    @RelaxedMockK
    lateinit var getMovieTrailerUseCase: GetMovieTrailerUseCase

    private lateinit var movieDetailsViewModel: MovieDetailsViewModel

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()


    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(Dispatchers.Unconfined)
        val savedStateHandle = SavedStateHandle().apply {
            set("movieId", "1")
        }
        movieDetailsViewModel =
            MovieDetailsViewModel(getMovieDetailsUseCase, getMovieTrailerUseCase, savedStateHandle)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `When detail use case success`() = runTest  {
        //Given
        val movieDetails = MovieDetails(
            SimpleMovieItem(1, "Movie Detail", null, null),
            "Movie Detail", null, null, null,
            null, null, null, null, null
        )

        val trailer = MovieVideo()

        coEvery { getMovieDetailsUseCase(1) } returns Resource.Success(movieDetails)
        coEvery { getMovieTrailerUseCase(1) } returns Resource.Success(trailer)

        //when
        movieDetailsViewModel.loadData()

        //Then
        assert(movieDetailsViewModel.movieDetailsResource.value?.data == movieDetails)
    }

    @Test
    fun `When detail use case failed`() = runTest  {
        //Given

        coEvery { getMovieDetailsUseCase(1) } returns Resource.GenericDataError()
        coEvery { getMovieTrailerUseCase(1) } returns Resource.GenericDataError()

        //when
        movieDetailsViewModel.loadData()

        //Then
        assert(movieDetailsViewModel.movieDetailsResource.value?.data == null)
    }

}