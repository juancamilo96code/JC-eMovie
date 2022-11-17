package com.jc.collantes.emovie.data.service.api

import com.jc.collantes.emovie.data.model.api.APIMovieDetail
import com.jc.collantes.emovie.data.model.api.ApiResponse
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class ApiServiceGeneratorTest{

    private lateinit var apiServiceGenerator: ApiServiceGenerator

    @Before
    fun onBefore(){
        apiServiceGenerator = ApiServiceGenerator("https://api.themoviedb.org/3/movie/", "175a7356eb593bbb3031e5bdd8f99903")
    }

    @Test
    fun createService(){
        val service = apiServiceGenerator.createService(MovieService::class.java)
        MatcherAssert.assertThat(
            service,
            CoreMatchers.instanceOf(MovieService::class.java)
        )
    }

    @Test
    fun processCallWithError() = runBlocking{
        val service = apiServiceGenerator.createService(MovieService::class.java)
        val callProcessed = apiServiceGenerator.processCallWithError {
            service.getMovieDetails(-1)
        }
        MatcherAssert.assertThat(
            callProcessed,
            CoreMatchers.instanceOf(ApiResponse::class.java)
        )
    }

}