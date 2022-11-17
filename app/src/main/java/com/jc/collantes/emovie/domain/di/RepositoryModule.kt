package com.jc.collantes.emovie.domain.di

import com.jc.collantes.emovie.data.datasource.*
import com.jc.collantes.emovie.data.mappers.*
import com.jc.collantes.emovie.data.repositories.*
import com.jc.collantes.emovie.data.service.local.MovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideMovieDetailsRepository(
        moviesDetailsDataSource: MovieDetailsDataSource,
        apiMovieDetailsToMovieDetailsMapper: APIMovieDetailsToMovieDetailsMapper,
        apiMovieDetailsToMovieDBMapper: APIMovieDetailsToMovieDBMapper,
        movieDBToMovieDetailsMapper: MovieDBToMovieDetailsMapper,
        movieDao: MovieDao
    ): MovieDetailsRepository {
        return MovieDetailsRepositoryImpl(
            moviesDetailsDataSource,
            apiMovieDetailsToMovieDetailsMapper,
            apiMovieDetailsToMovieDBMapper,
            movieDBToMovieDetailsMapper,
            movieDao,
            Dispatchers.IO
        )
    }

    @Provides
    @Singleton
    fun provideMovieVideoRepository(
        movieVideosDataSource: MovieVideosDataSource,
        apiMovieVideosToMovieVideosMapper: APIMovieVideosToMovieVideosMapper,
        movieDao: MovieDao,
    ): MovieVideoRepository {
        return MovieVideoRepositoryImpl(
            movieVideosDataSource,
            apiMovieVideosToMovieVideosMapper,
            movieDao,
            Dispatchers.IO
        )
    }


    @Provides
    @Singleton
    fun provideTopRatedMoviesRepository(
        topRatedMoviesDataSource: TopRatedMoviesDataSource,
        apiMovieToSimpleMovieItemMapper: APIMovieToSimpleMovieItemMapper,
        apiMovieToMovieDBMapper: APIMovieToMovieDBMapper,
        movieDBToSimpleMovieMapper : MovieDBToSimpleMovieMapper,
        movieDao: MovieDao,
    ): TopRatedMoviesRepository {
        return TopRatedMoviesRepositoryImpl(
            topRatedMoviesDataSource,
            apiMovieToSimpleMovieItemMapper,
            apiMovieToMovieDBMapper,
            movieDBToSimpleMovieMapper,
            movieDao,
            Dispatchers.IO
        )
    }

    @Provides
    @Singleton
    fun provideUpcomingMoviesRepository(
        moviesUpcomingMoviesDataSource: UpcomingMoviesDataSource,
        apiMovieToSimpleMovieItemMapper: APIMovieToSimpleMovieItemMapper,
        apiMovieToMovieDBMapper: APIMovieToMovieDBMapper,
        movieDBToSimpleMovieMapper : MovieDBToSimpleMovieMapper,
        movieDao: MovieDao,
    ): UpcomingMoviesRepository {
        return UpcomingMoviesRepositoryImpl(
            moviesUpcomingMoviesDataSource,
            apiMovieToSimpleMovieItemMapper,
            apiMovieToMovieDBMapper,
            movieDBToSimpleMovieMapper,
            movieDao,
            Dispatchers.IO
        )
    }

}