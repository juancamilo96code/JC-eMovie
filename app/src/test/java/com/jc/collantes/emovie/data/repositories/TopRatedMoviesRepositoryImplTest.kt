package com.jc.collantes.emovie.data.repositories

import com.jc.collantes.emovie.data.datasource.TopRatedMoviesDataSource
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

class TopRatedMoviesRepositoryImplTest{

    @MockK(relaxed = true)
    lateinit var topRatedMoviesDataSource: TopRatedMoviesDataSource

    @MockK(relaxed = true)
    lateinit var movieDao: MovieDao

    private lateinit var apiMovieToSimpleMovieItemMapper: APIMovieToSimpleMovieItemMapper

    private lateinit var apiMovieToMovieDBMapper: APIMovieToMovieDBMapper

    private lateinit var movieDBToSimpleMovieMapper : MovieDBToSimpleMovieMapper

    private lateinit var topRatedMoviesRepository: TopRatedMoviesRepository

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        apiMovieToSimpleMovieItemMapper = APIMovieToSimpleMovieItemMapperImpl()
        apiMovieToMovieDBMapper = APIMovieToMovieDBMapperImpl()
        movieDBToSimpleMovieMapper = MovieDBToSimpleMovieMapperImpl()
        topRatedMoviesRepository = TopRatedMoviesRepositoryImpl(
            topRatedMoviesDataSource,
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
        coEvery { topRatedMoviesDataSource.getTopRatedMovies() } returns RemoteMockData.getMovieList()
        //When
        val repositoryResponse = topRatedMoviesRepository.getTopRatedMovies()
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
        coEvery { topRatedMoviesDataSource.getTopRatedMovies()} returns Resource.GenericDataError()
        coEvery { movieDao.getTopRatedMovies() } returns LocalMockData.getFakeMovieDetailEditedlList()

        //When
        val repositoryResponse = topRatedMoviesRepository.getTopRatedMovies()
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
        coEvery {topRatedMoviesDataSource.getTopRatedMovies() } returns Resource.GenericDataError(404,"error")
        coEvery { movieDao.getTopRatedMovies() } returns null

        //When
        val repositoryResponse = topRatedMoviesRepository.getTopRatedMovies()
        var result : Resource<List<SimpleMovieItem>>  = Resource.Success(null)
        repositoryResponse.collect {
            result = it
        }

        //Then
        assert(result.errorCode == 404)
    }

}