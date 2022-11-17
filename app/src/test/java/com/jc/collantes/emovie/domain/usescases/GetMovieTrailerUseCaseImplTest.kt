package com.jc.collantes.emovie.domain.usescases

import com.jc.collantes.emovie.data.model.Resource
import com.jc.collantes.emovie.data.model.ui.MovieVideo
import com.jc.collantes.emovie.data.repositories.MovieVideoRepository
import com.jc.collantes.emovie.utils.TRAILER_TYPE
import com.jc.collantes.emovie.utils.YOUTUBE_SITE
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetMovieTrailerUseCaseImplTest{

    @MockK
    private lateinit var movieVideoRepository: MovieVideoRepository

    lateinit var getMovieTrailerUseCase: GetMovieTrailerUseCase

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        getMovieTrailerUseCase = GetMovieTrailerUseCaseImpl(movieVideoRepository)
    }

    @Test
    fun  `When the trailer Repository Response GenericError`() = runBlocking{
        //Given
        val resource =
            flow<Resource<List<MovieVideo?>>> {
                emit(Resource.GenericDataError())
            }.flowOn(Dispatchers.IO)

        coEvery { movieVideoRepository.getMovieVideos(1)} returns resource

        //When
        getMovieTrailerUseCase(1)

        //Then
        coVerify (exactly = 1){
            movieVideoRepository.getMovieVideos(1)
        }

    }

    @Test
    fun `When the trailer Repository Response one item Success `() = runBlocking{
        //Given
        val listVideos = listOf(MovieVideo(site = YOUTUBE_SITE, type = TRAILER_TYPE))
        val resource =
            flow<Resource<List<MovieVideo?>>> {
                emit(Resource.Success(listVideos))
            }.flowOn(Dispatchers.IO)

        coEvery { movieVideoRepository.getMovieVideos(1)} returns resource

        //When
        val response = getMovieTrailerUseCase(1)

        //Then
        coVerify (exactly = 1){
            movieVideoRepository.getMovieVideos(1)
        }
        assert(response.data == listVideos[0])

    }


    @Test
    fun  `When the trailer Repository Response empty list Success`() = runBlocking{
        //Given
        val listVideos = listOf(MovieVideo(), MovieVideo())
        val resource =
            flow<Resource<List<MovieVideo?>>> {
                emit(Resource.Success(listVideos))
            }.flowOn(Dispatchers.IO)

        coEvery { movieVideoRepository.getMovieVideos(1)} returns resource

        //When
        getMovieTrailerUseCase(1)

        //Then
        coVerify (exactly = 1){
            movieVideoRepository.getMovieVideos(1)
        }

    }


}