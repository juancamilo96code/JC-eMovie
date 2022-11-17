package com.jc.collantes.emovie.domain.di

import com.jc.collantes.emovie.data.repositories.*
import com.jc.collantes.emovie.domain.usescases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideGetMovieDetailsUseCase(
        moviesDetailsRepository: MovieDetailsRepository
    ): GetMovieDetailsUseCase {
        return GetMovieDetailsUseCaseImpl(
            moviesDetailsRepository
        )
    }

    @Provides
    @Singleton
    fun provideGetMovieTrailerUseCase(
        movieVideoRepository: MovieVideoRepository
    ): GetMovieTrailerUseCase {
        return GetMovieTrailerUseCaseImpl(
            movieVideoRepository
        )
    }

    @Provides
    @Singleton
    fun provideGetRecommendedMoviesFilteredUseCase(
        topRatedMoviesRepository: TopRatedMoviesRepository
    ): GetRecommendedMoviesFilteredUseCase {
        return GetRecommendedMoviesFilteredUseCaseImpl(
            topRatedMoviesRepository
        )
    }

    @Provides
    @Singleton
    fun provideGetTopRatedMoviesUseCase(
        topRatedMoviesRepository: TopRatedMoviesRepository
    ): GetTopRatedMoviesUseCase {
        return GetTopRatedMoviesUseCaseImpl(
            topRatedMoviesRepository
        )
    }

    @Provides
    @Singleton
    fun provideGetUpComingMoviesUseCase(
        upcomingMoviesRepository: UpcomingMoviesRepository
    ): GetUpComingMoviesUseCase {
        return GetUpComingMoviesUseCaseImpl(
            upcomingMoviesRepository
        )
    }

}