package com.jc.collantes.emovie.data.datasource

import com.jc.collantes.emovie.data.service.api.ApiServiceGenerator
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class MovieDetailsDataSourceImplTest{

    private val apiServiceGenerator: ApiServiceGenerator = ApiServiceGenerator("https://api.themoviedb.org/3/movie/", "175a7356eb593bbb3031e5bdd8f99903")

    private lateinit var movieDetailsDataSource: MovieDetailsDataSource

    @Before
    fun onBefore(){
        movieDetailsDataSource = MovieDetailsDataSourceImpl(
            apiServiceGenerator
        )
    }

    @Test
    fun getMovieDetails() = runBlocking {
        //When
        val response = movieDetailsDataSource.getMovieDetails(-1)
        //Then
        assert(response.errorCode == 404)
    }

}