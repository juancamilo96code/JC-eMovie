package com.jc.collantes.emovie.domain.usescases

import com.jc.collantes.emovie.data.model.Resource
import com.jc.collantes.emovie.data.model.ui.SimpleMovieItem
import com.jc.collantes.emovie.data.repositories.UpcomingMoviesRepository
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

class GetUpComingMoviesUseCaseImplTest {

    @MockK
    private lateinit var upcomingMoviesRepository: UpcomingMoviesRepository

    lateinit var getUpComingMoviesUseCase: GetUpComingMoviesUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getUpComingMoviesUseCase =
            GetUpComingMoviesUseCaseImpl(upcomingMoviesRepository)
    }

    private val fakeList = listOf(
        SimpleMovieItem(1, "", "", originalLanguage = "es", releaseDate = "1995-03-04"),
        SimpleMovieItem(1, "", "", originalLanguage = "es"),
        SimpleMovieItem(1, "", "", originalLanguage = "es", releaseDate = "1995-03-04"),
        SimpleMovieItem(1, "", ""),
        SimpleMovieItem(1, "", "", releaseDate = "1995-03-04"),
        SimpleMovieItem(1, "", "", originalLanguage = "es"),
        SimpleMovieItem(1, "", "", releaseDate = "1995-03-04"),
        SimpleMovieItem(1, "", "", originalLanguage = "es")
    )

    @Test
    fun `When the top rated Repository Response `() = runBlocking {
        //Given
        val resource =
            flow<Resource<List<SimpleMovieItem>>> {
                emit(Resource.Success(fakeList))
            }.flowOn(Dispatchers.IO)

        coEvery { upcomingMoviesRepository.getUpcomingMovies()} returns resource

        //When
        val response = getUpComingMoviesUseCase()

        //Then
        coVerify(exactly = 1) { upcomingMoviesRepository.getUpcomingMovies() }
        assert(response.data?.size == 8)

    }
}
