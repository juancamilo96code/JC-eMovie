package com.jc.collantes.emovie.domain.di

import com.jc.collantes.emovie.data.repositories.*
import com.jc.collantes.emovie.domain.usescases.*
import io.mockk.MockKAnnotations
import io.mockk.mockk
import org.junit.Before
import org.junit.Test

class UseCaseModuleTest {

    private lateinit var useCaseModule: UseCaseModule

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        useCaseModule = UseCaseModule
    }

    @Test
    fun provideGetMovieDetailsUseCase() {
        val movieDetailsRepository = mockk<MovieDetailsRepository>()
        val useCase = useCaseModule.provideGetMovieDetailsUseCase(movieDetailsRepository)
        assert(useCase is GetMovieDetailsUseCaseImpl)
    }

    @Test
    fun provideGetMovieTrailerUseCase() {
        val movieVideoRepository = mockk<MovieVideoRepository>()
        val useCase = useCaseModule.provideGetMovieTrailerUseCase(movieVideoRepository)
        assert(useCase is GetMovieTrailerUseCaseImpl)
    }

    @Test
    fun provideGetRecommendedMoviesFilteredUseCase() {
        val recommendedMoviesRepository = mockk<TopRatedMoviesRepository>()
        val useCase = useCaseModule.provideGetRecommendedMoviesFilteredUseCase(recommendedMoviesRepository)
        assert(useCase is GetRecommendedMoviesFilteredUseCaseImpl)
    }

    @Test
    fun provideGetTopRatedMoviesUseCase() {
        val topRatedMoviesRepository = mockk<TopRatedMoviesRepository>()
        val useCase = useCaseModule.provideGetTopRatedMoviesUseCase(topRatedMoviesRepository)
        assert(useCase is GetTopRatedMoviesUseCaseImpl)
    }

    @Test
    fun provideGetUpComingMoviesUseCase() {
        val upcomingMoviesRepository = mockk<UpcomingMoviesRepository>()
        val useCase = useCaseModule.provideGetUpComingMoviesUseCase(upcomingMoviesRepository)
        assert(useCase is GetUpComingMoviesUseCaseImpl)
    }
}