package com.jc.collantes.emovie.data.repositories

import android.os.Build
import com.jc.collantes.emovie.data.datasource.MovieDetailsDataSource
import com.jc.collantes.emovie.data.mappers.*
import com.jc.collantes.emovie.data.model.Resource
import com.jc.collantes.emovie.data.model.local.LocalMockData
import com.jc.collantes.emovie.data.model.remote.RemoteMockData
import com.jc.collantes.emovie.data.model.ui.MovieDetails
import com.jc.collantes.emovie.data.service.local.MovieDao
import com.jc.collantes.emovie.data.service.local.MovieDaoTest
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

class MovieDetailsRepositoryImplTest{

    @MockK(relaxed = true)
    lateinit var moviesDetailsDataSource: MovieDetailsDataSource

    @MockK(relaxed = true)
    lateinit var movieDao: MovieDao

    private lateinit var apiMovieDetailsToMovieDetailsMapper: APIMovieDetailsToMovieDetailsMapper

    private lateinit var apiMovieDetailsToMovieDBMapper: APIMovieDetailsToMovieDBMapper

    private lateinit var movieDBToMovieDetailsMapper: MovieDBToMovieDetailsMapper

    private lateinit var repository: MovieDetailsRepository

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        apiMovieDetailsToMovieDBMapper = APIMovieDetailsToMovieDBMapperImpl()
        apiMovieDetailsToMovieDetailsMapper = APIMovieDetailsToMovieDetailsMapperImpl()
        movieDBToMovieDetailsMapper = MovieDBToMovieDetailsMapperImpl()
        repository = MovieDetailsRepositoryImpl(
            moviesDetailsDataSource,
            apiMovieDetailsToMovieDetailsMapper,
            apiMovieDetailsToMovieDBMapper,
            movieDBToMovieDetailsMapper,
            movieDao,
            Dispatchers.IO
        )
    }

    @Test
    fun getMovieDetailsRemoteSuccess()= runBlocking{
        //Given
        coEvery { moviesDetailsDataSource.getMovieDetails(1) } returns RemoteMockData.getMovieDetail()
        //When
        val repositoryResponse = repository.getMovieDetails(1)
        var result : Resource<MovieDetails?> = Resource.Success(null)
        repositoryResponse.collect {
            result = it
        }

        //Then
        assert(result.data?.originalTitle == Resource.Success(apiMovieDetailsToMovieDetailsMapper.map(RemoteMockData.getMovieDetail().data)).data?.originalTitle)

    }

    @Test
    fun getMovieDetailsLocalSuccess()= runBlocking{
        //Given
        coEvery { moviesDetailsDataSource.getMovieDetails(1) } returns Resource.GenericDataError()
        coEvery { movieDao.getMovieDetails(1) } returns LocalMockData.getFakeMovieDetailEdited()

        //When
        val repositoryResponse = repository.getMovieDetails(1)
        var result : Resource<MovieDetails?> = Resource.Success(null)
        repositoryResponse.collect {
            result = it
        }

        //Then
        assert(result.data?.originalTitle == Resource.Success(movieDBToMovieDetailsMapper.map(LocalMockData.getFakeMovieDetailEdited())).data?.originalTitle)

    }

    @Test
    fun getMovieDetailsFailed()= runBlocking{
        //Given
        //Given
        coEvery { moviesDetailsDataSource.getMovieDetails(1) } returns Resource.GenericDataError(404,"error")
        coEvery { movieDao.getMovieDetails(1) } returns null

        //When
        val repositoryResponse = repository.getMovieDetails(1)
        var result : Resource<MovieDetails?> = Resource.Success(null)
        repositoryResponse.collect {
            result = it
        }

        //Then
        assert(result.errorCode == 404)
    }

}