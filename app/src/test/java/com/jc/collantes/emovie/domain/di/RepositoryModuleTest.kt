package com.jc.collantes.emovie.domain.di

import com.jc.collantes.emovie.data.datasource.*
import com.jc.collantes.emovie.data.mappers.*
import com.jc.collantes.emovie.data.repositories.*
import com.jc.collantes.emovie.data.service.local.MovieDao
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test

class RepositoryModuleTest {

    lateinit var repositoryModule : RepositoryModule

    @MockK
    lateinit var movieDao: MovieDao

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        repositoryModule = RepositoryModule
    }

    @Test
    fun provideMovieDetailsRepository() {
        val moviesDetailsDataSource: MovieDetailsDataSource  = mockk()
        val apiMovieDetailsToMovieDetailsMapper: APIMovieDetailsToMovieDetailsMapper = mockk()
        val apiMovieDetailsToMovieDBMapper: APIMovieDetailsToMovieDBMapper = mockk()
        val movieDBToMovieDetailsMapper: MovieDBToMovieDetailsMapper = mockk()
        val repository = repositoryModule.provideMovieDetailsRepository(
            moviesDetailsDataSource,
            apiMovieDetailsToMovieDetailsMapper,
            apiMovieDetailsToMovieDBMapper,
            movieDBToMovieDetailsMapper,
            movieDao
        )
        assert(repository is MovieDetailsRepositoryImpl)
    }

    @Test
    fun provideMovieVideoRepository() {
        val movieVideosDataSource: MovieVideosDataSource = mockk()
        val apiMovieVideosToMovieVideosMapper: APIMovieVideosToMovieVideosMapper = mockk()
        val repository = repositoryModule.provideMovieVideoRepository(
            movieVideosDataSource,
            apiMovieVideosToMovieVideosMapper,
            movieDao
        )
        assert(repository is MovieVideoRepositoryImpl)
    }


    @Test
    fun provideTopRatedMoviesRepository() {
        val topRatedMoviesDataSource: TopRatedMoviesDataSource = mockk()
        val apiMovieToSimpleMovieItemMapper: APIMovieToSimpleMovieItemMapper = mockk()
        val apiMovieToMovieDBMapper: APIMovieToMovieDBMapper = mockk()
        val movieDBToSimpleMovieMapper : MovieDBToSimpleMovieMapper = mockk()
        val repository = repositoryModule.provideTopRatedMoviesRepository(
            topRatedMoviesDataSource,
            apiMovieToSimpleMovieItemMapper,
            apiMovieToMovieDBMapper,
            movieDBToSimpleMovieMapper,
            movieDao
        )
        assert(repository is TopRatedMoviesRepositoryImpl)
    }

    @Test
    fun provideUpcomingMoviesRepository() {
        val moviesUpcomingMoviesDataSource: UpcomingMoviesDataSource = mockk()
        val apiMovieToSimpleMovieItemMapper: APIMovieToSimpleMovieItemMapper = mockk()
        val apiMovieToMovieDBMapper: APIMovieToMovieDBMapper = mockk()
        val movieDBToSimpleMovieMapper : MovieDBToSimpleMovieMapper = mockk()
        val repository = repositoryModule.provideUpcomingMoviesRepository(
            moviesUpcomingMoviesDataSource,
            apiMovieToSimpleMovieItemMapper,
            apiMovieToMovieDBMapper,
            movieDBToSimpleMovieMapper,
            movieDao
        )
        assert(repository is UpcomingMoviesRepositoryImpl)
    }
}