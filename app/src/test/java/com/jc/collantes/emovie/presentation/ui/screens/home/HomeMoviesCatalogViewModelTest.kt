package com.jc.collantes.emovie.presentation.ui.screens.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jc.collantes.emovie.data.model.Resource
import com.jc.collantes.emovie.data.model.ui.SimpleMovieItem
import com.jc.collantes.emovie.domain.usescases.GetRecommendedMoviesFilteredUseCase
import com.jc.collantes.emovie.domain.usescases.GetTopRatedMoviesUseCase
import com.jc.collantes.emovie.domain.usescases.GetUpComingMoviesUseCase
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
class HomeMoviesCatalogViewModelTest{

    @RelaxedMockK
    lateinit var getUpComingMoviesUseCase: GetUpComingMoviesUseCase

    @RelaxedMockK
    lateinit var getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase

    @RelaxedMockK
    lateinit var getRecommendedMoviesFilteredUseCase: GetRecommendedMoviesFilteredUseCase

    private lateinit var homeMoviesCatalogViewModel: HomeMoviesCatalogViewModel

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        Dispatchers.setMain(Dispatchers.Unconfined)
        homeMoviesCatalogViewModel = HomeMoviesCatalogViewModel(
            getUpComingMoviesUseCase,
            getTopRatedMoviesUseCase,
            getRecommendedMoviesFilteredUseCase
        )
    }

    @After
    fun onAfter(){
        Dispatchers.resetMain()
    }

    @Test
    fun `When all use case success`() = runTest {
        //Given
        val movieList = listOf(SimpleMovieItem(1,"Movie",null,null,null,null))
        coEvery { getUpComingMoviesUseCase() } returns Resource.Success(movieList)
        coEvery { getTopRatedMoviesUseCase() } returns Resource.Success(movieList)
        coEvery { getRecommendedMoviesFilteredUseCase() } returns Resource.Success(movieList)
        //When
        homeMoviesCatalogViewModel.loadData()
        //Then
        assert(homeMoviesCatalogViewModel.emptyState.value == false)
    }

    @Test
    fun `When one use case fail`()= runTest {
        //Given
        val movieList = listOf(SimpleMovieItem(1,"Movie",null,null,null,null))
        coEvery { getUpComingMoviesUseCase() } returns Resource.Success(movieList)
        coEvery { getTopRatedMoviesUseCase() } returns Resource.Success(movieList)
        coEvery { getRecommendedMoviesFilteredUseCase() } returns Resource.GenericDataError()
        //When
        homeMoviesCatalogViewModel.loadData()
        //Then
        assert(homeMoviesCatalogViewModel.emptyState.value == false)
    }

    @Test
    fun `When all use case failed`()= runTest {
        //Given
        coEvery { getUpComingMoviesUseCase() } returns Resource.GenericDataError()
        coEvery { getTopRatedMoviesUseCase() } returns Resource.GenericDataError()
        coEvery { getRecommendedMoviesFilteredUseCase() } returns Resource.GenericDataError()
        //When
        homeMoviesCatalogViewModel.loadData()
        //Then
        val isD = homeMoviesCatalogViewModel.emptyState.value
        assert(isD == true)

    }

}