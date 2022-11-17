package com.jc.collantes.emovie.data.datasource

import com.jc.collantes.emovie.data.service.api.ApiServiceGenerator
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class TopRatedMoviesDataSourceImplTest{

    private val apiServiceGenerator: ApiServiceGenerator = ApiServiceGenerator("https://api.themoviedb.org/3/movie/", "175a7356eb593bbb3031e5bdd8f99903")

    private lateinit var topRatedMoviesDataSource: TopRatedMoviesDataSource

    @Before
    fun onBefore(){
        topRatedMoviesDataSource = TopRatedMoviesDataSourceImpl(
            apiServiceGenerator
        )
    }

    @Test
    fun getMovieDetails() = runBlocking {
        //When
        val response = topRatedMoviesDataSource.getTopRatedMovies()
        //Then
        assert(response.data != null)
    }
}