package com.jc.collantes.emovie.domain.usescases

import com.jc.collantes.emovie.data.model.Resource
import com.jc.collantes.emovie.data.model.ui.SimpleMovieItem
import com.jc.collantes.emovie.data.repositories.TopRatedMoviesRepository
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

class GetRecommendedMoviesFilteredUseCaseImplTest {

    @MockK
    private lateinit var topRatedMoviesRepository: TopRatedMoviesRepository

    lateinit var getRecommendedMoviesFilteredUseCase: GetRecommendedMoviesFilteredUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getRecommendedMoviesFilteredUseCase =
            GetRecommendedMoviesFilteredUseCaseImpl(topRatedMoviesRepository)
    }

    private val fakeList = listOf(
        SimpleMovieItem(1, "", "", originalLanguage = "es", releaseDate = "1995-03-04"),
        SimpleMovieItem(1, "", "",originalLanguage = "es"),
        SimpleMovieItem(1, "", "",originalLanguage = "es", releaseDate = "1995-03-04"),
        SimpleMovieItem(1, "", ""),
        SimpleMovieItem(1, "", "",releaseDate = "1995-03-04"),
        SimpleMovieItem(1, "", "",originalLanguage = "es"),
        SimpleMovieItem(1, "", "",releaseDate = "1995-03-04"),
        SimpleMovieItem(1, "", "",originalLanguage = "es")
    )

    @Test
    fun `When the recommended Repository Response Without filter`() = runBlocking {
        //Given
        val resource =
            flow<Resource<List<SimpleMovieItem>>> {
                emit(Resource.Success(fakeList))
            }.flowOn(Dispatchers.IO)

        coEvery { topRatedMoviesRepository.getTopRatedMovies() } returns resource

        //When
        val response = getRecommendedMoviesFilteredUseCase(null, null)

        //Then
        coVerify(exactly = 1) { topRatedMoviesRepository.getTopRatedMovies() }
        assert(response.data?.size == 6)

    }

    @Test
    fun `When the details Repository Response With language filter`() = runBlocking {
        //Given
        val resource =
            flow<Resource<List<SimpleMovieItem>>> {
                emit(Resource.Success(fakeList))
            }.flowOn(Dispatchers.IO)

        coEvery { topRatedMoviesRepository.getTopRatedMovies() } returns resource

        //When
        val response = getRecommendedMoviesFilteredUseCase(null, "es")

        //Then
        coVerify(exactly = 1) { topRatedMoviesRepository.getTopRatedMovies() }
        assert(response.data?.size == 5)

    }

    @Test
    fun `When the details Repository Response With year filter`() = runBlocking {
        //Given
        val resource =
            flow<Resource<List<SimpleMovieItem>>> {
                emit(Resource.Success(fakeList))
            }.flowOn(Dispatchers.IO)

        coEvery { topRatedMoviesRepository.getTopRatedMovies() } returns resource

        //When
        val response = getRecommendedMoviesFilteredUseCase("1995", null)

        //Then
        coVerify(exactly = 1) { topRatedMoviesRepository.getTopRatedMovies()}
        assert(response.data?.size == 4)

    }

    @Test
    fun `When the details Repository Response With both filter`() = runBlocking {
        //Given
        val resource =
            flow<Resource<List<SimpleMovieItem>>> {
                emit(Resource.Success(fakeList))
            }.flowOn(Dispatchers.IO)

        coEvery { topRatedMoviesRepository.getTopRatedMovies() } returns resource

        //When
        val response = getRecommendedMoviesFilteredUseCase("1995", "es")

        //Then
        coVerify(exactly = 1) { topRatedMoviesRepository.getTopRatedMovies() }
        assert(response.data?.size == 2)

    }
}