package com.jc.collantes.emovie.data.repositories

import com.jc.collantes.emovie.data.datasource.MovieVideosDataSource
import com.jc.collantes.emovie.data.mappers.APIMovieVideosToMovieVideosMapper
import com.jc.collantes.emovie.data.mappers.APIMovieVideosToMovieVideosMapperImpl
import com.jc.collantes.emovie.data.model.Resource
import com.jc.collantes.emovie.data.model.local.LocalMockData
import com.jc.collantes.emovie.data.model.remote.RemoteMockData
import com.jc.collantes.emovie.data.model.ui.MovieVideo
import com.jc.collantes.emovie.data.service.local.MovieDao
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class MovieVideoRepositoryImplTest{

    @MockK(relaxed = true)
    lateinit var movieVideosDataSource: MovieVideosDataSource

    @MockK(relaxed = true)
    lateinit var movieDao: MovieDao

    private lateinit var  apiMovieVideosToMovieVideosMapper: APIMovieVideosToMovieVideosMapper

    private lateinit var movieVideoRepository: MovieVideoRepository

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        apiMovieVideosToMovieVideosMapper = APIMovieVideosToMovieVideosMapperImpl()
        movieVideoRepository = MovieVideoRepositoryImpl(
            movieVideosDataSource,
            apiMovieVideosToMovieVideosMapper,
            movieDao,
            Dispatchers.IO
        )
    }

    @Test
    fun getMovieVideosRemoteSuccess()= runBlocking{
        //Given
        coEvery { movieVideosDataSource.getMovieVideos(1) } returns RemoteMockData.getMovieVideos()
        //When
        val repositoryResponse = movieVideoRepository.getMovieVideos(1)
        var result : Resource<List<MovieVideo?>> = Resource.Success(null)
        repositoryResponse.collect {
            result = it
        }

        //Then
        assert(result.data?.size == RemoteMockData.getMovieVideos().data?.results?.size)

    }

    @Test
    fun getMovieVideosLocalSuccess()= runBlocking{
        //Given
        coEvery { movieVideosDataSource.getMovieVideos(1)} returns Resource.GenericDataError()
        coEvery { movieDao.getMovieDetails(1) } returns LocalMockData.getFakeMovieDetailEdited()

        //When
        val repositoryResponse = movieVideoRepository.getMovieVideos(1)
        var result : Resource<List<MovieVideo?>> = Resource.Success(null)
        repositoryResponse.collect {
            result = it
        }

        //Then
        assert(result.data.isNullOrEmpty())

    }

    @Test
    fun getMovieVideosFailed()= runBlocking{
        //Given
        coEvery { movieVideosDataSource.getMovieVideos(1) } returns Resource.GenericDataError(404,"error")
        coEvery { movieDao.getMovieDetails(1) } returns null

        //When
        val repositoryResponse = movieVideoRepository.getMovieVideos(1)
        var result : Resource<List<MovieVideo?>>  = Resource.Success(null)
        repositoryResponse.collect {
            result = it
        }

        //Then
        assert(result.errorCode == 404)
    }

}