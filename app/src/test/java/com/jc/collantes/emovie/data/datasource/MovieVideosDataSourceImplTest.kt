package com.jc.collantes.emovie.data.datasource

import com.jc.collantes.emovie.data.service.api.ApiServiceGenerator
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class MovieVideosDataSourceImplTest{

    private val apiServiceGenerator: ApiServiceGenerator = ApiServiceGenerator("https://api.themoviedb.org/3/movie/", "175a7356eb593bbb3031e5bdd8f99903")

    private lateinit var movieVideosDataSource: MovieVideosDataSource

    @Before
    fun onBefore(){
        movieVideosDataSource = MovieVideosDataSourceImpl(
            apiServiceGenerator
        )
    }

    @Test
    fun getMovieDetails() = runBlocking {
        //When
        val response = movieVideosDataSource.getMovieVideos(-1)
        //Then
        assert(response.errorCode == 404)
    }

}