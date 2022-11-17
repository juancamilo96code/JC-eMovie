package com.jc.collantes.emovie.domain.di

import com.jc.collantes.emovie.data.datasource.*
import com.jc.collantes.emovie.data.service.api.ApiServiceGenerator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideMovieVideosDataSource(apiServiceGenerator: ApiServiceGenerator): MovieVideosDataSource {
        return MovieVideosDataSourceImpl(apiServiceGenerator)
    }

    @Provides
    @Singleton
    fun provideTopRatedMoviesDataSource(apiServiceGenerator: ApiServiceGenerator): TopRatedMoviesDataSource {
        return TopRatedMoviesDataSourceImpl(apiServiceGenerator)
    }

    @Provides
    @Singleton
    fun provideUpcomingMoviesDataSource(apiServiceGenerator: ApiServiceGenerator): UpcomingMoviesDataSource {
        return UpcomingMoviesDataSourceImpl(apiServiceGenerator)
    }

    @Provides
    @Singleton
    fun provideMovieDetailsDataSource(apiServiceGenerator: ApiServiceGenerator): MovieDetailsDataSource {
        return MovieDetailsDataSourceImpl(apiServiceGenerator)
    }
}