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

class GetTopRatedMoviesUseCaseImplTest{

    @MockK
    private lateinit var topRatedMoviesRepository: TopRatedMoviesRepository

    lateinit var getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getTopRatedMoviesUseCase =
            GetTopRatedMoviesUseCaseImpl(topRatedMoviesRepository)
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
    fun `When the top rated Repository Response `() = runBlocking {
        //Given
        val resource =
            flow<Resource<List<SimpleMovieItem>>> {
                emit(Resource.Success(fakeList))
            }.flowOn(Dispatchers.IO)

        coEvery { topRatedMoviesRepository.getTopRatedMovies() } returns resource

        //When
        val response = getTopRatedMoviesUseCase()

        //Then
        coVerify(exactly = 1) {topRatedMoviesRepository.getTopRatedMovies() }
        assert(response.data?.size == 8)

    }

}