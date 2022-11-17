package com.jc.collantes.emovie.data.repositories

import com.jc.collantes.emovie.data.datasource.UpcomingMoviesDataSource
import com.jc.collantes.emovie.data.mappers.*
import com.jc.collantes.emovie.data.model.Resource
import com.jc.collantes.emovie.data.model.local.LocalMockData
import com.jc.collantes.emovie.data.model.remote.RemoteMockData
import com.jc.collantes.emovie.data.model.ui.SimpleMovieItem
import com.jc.collantes.emovie.data.service.local.MovieDao
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class UpcomingMoviesRepositoryImplTest{

    @MockK(relaxed = true)
    lateinit var upcomingMoviesDataSource: UpcomingMoviesDataSource

    @MockK(relaxed = true)
    lateinit var movieDao: MovieDao

    private lateinit var apiMovieToSimpleMovieItemMapper: APIMovieToSimpleMovieItemMapper

    private lateinit var apiMovieToMovieDBMapper: APIMovieToMovieDBMapper

    private lateinit var movieDBToSimpleMovieMapper : MovieDBToSimpleMovieMapper

    private lateinit var upcomingMoviesRepository: UpcomingMoviesRepository

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        apiMovieToSimpleMovieItemMapper = APIMovieToSimpleMovieItemMapperImpl()
        apiMovieToMovieDBMapper = APIMovieToMovieDBMapperImpl()
        movieDBToSimpleMovieMapper = MovieDBToSimpleMovieMapperImpl()
        upcomingMoviesRepository = UpcomingMoviesRepositoryImpl(
            upcomingMoviesDataSource,
            apiMovieToSimpleMovieItemMapper,
            apiMovieToMovieDBMapper,
            movieDBToSimpleMovieMapper,
            movieDao,
            Dispatchers.IO
        )
    }

    @Test
    fun getTopRatedMoviesRemoteSuccess()= runBlocking{
        //Given
        coEvery { upcomingMoviesDataSource.getUpcomingMovies() } returns RemoteMockData.getMovieList()
        //When
        val repositoryResponse = upcomingMoviesRepository.getUpcomingMovies()
        var result : Resource<List<SimpleMovieItem>> = Resource.Success(null)
        repositoryResponse.collect {
            result = it
        }

        //Then
        assert(result.data?.size == RemoteMockData.getMovieList().data?.results?.size)

    }

    @Test
    fun getTopRatedMoviesLocalSuccess()= runBlocking{
        //Given
        coEvery { upcomingMoviesDataSource.getUpcomingMovies()} returns Resource.GenericDataError()
        coEvery { movieDao.getUpcomingMovies() } returns LocalMockData.getFakeMovieDetailEditedlList()

        //When
        val repositoryResponse = upcomingMoviesRepository.getUpcomingMovies()
        var result : Resource<List<SimpleMovieItem>> = Resource.Success(null)
        repositoryResponse.collect {
            result = it
        }

        //Then
        assert(result.data?.size == LocalMockData.getFakeMovieDetailEditedlList().size)

    }

    @Test
    fun getTopRatedMoviesFailed()= runBlocking{
        //Given
        coEvery {upcomingMoviesDataSource.getUpcomingMovies() } returns Resource.GenericDataError(404,"error")
        coEvery { movieDao.getUpcomingMovies() } returns null

        //When
        val repositoryResponse = upcomingMoviesRepository.getUpcomingMovies()
        var result : Resource<List<SimpleMovieItem>>  = Resource.Success(null)
        repositoryResponse.collect {
            result = it
        }

        //Then
        assert(result.errorCode == 404)
    }
}