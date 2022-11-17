package com.jc.collantes.emovie.domain.usescases

import com.jc.collantes.emovie.data.model.Resource
import com.jc.collantes.emovie.data.model.ui.MovieDetails
import com.jc.collantes.emovie.data.repositories.MovieDetailsRepository
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

class GetMovieDetailsUseCaseImplTest {
    @MockK
    private  lateinit var moviesDetailsRepository: MovieDetailsRepository

    lateinit var getMovieDetailsUseCase : GetMovieDetailsUseCase

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        getMovieDetailsUseCase = GetMovieDetailsUseCaseImpl(moviesDetailsRepository)
    }

    @Test
    fun  `When the details Repository Response Any`() = runBlocking{
        //Given
        val resource =
            flow<Resource<MovieDetails?>> {
                emit(Resource.GenericDataError())
            }.flowOn(Dispatchers.IO)

        coEvery { moviesDetailsRepository.getMovieDetails(1)} returns resource

        //When
        getMovieDetailsUseCase(1)

        //Then
        coVerify (exactly = 1){moviesDetailsRepository.getMovieDetails(1)}

    }
}

