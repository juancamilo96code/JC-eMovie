package com.jc.collantes.emovie.data.datasource

import com.jc.collantes.emovie.data.model.Resource
import com.jc.collantes.emovie.data.model.api.APIMoviesListBaseResponse
import com.jc.collantes.emovie.data.service.api.ApiServiceGenerator
import com.jc.collantes.emovie.data.service.api.MovieService
import com.jc.collantes.emovie.utils.ResourceBuilder

class UpcomingMoviesDataSourceImpl(
    private val apiServiceGenerator: ApiServiceGenerator
) : UpcomingMoviesDataSource {

    private val movieService = apiServiceGenerator.createService(MovieService::class.java)

    override suspend fun getUpcomingMovies(): Resource<APIMoviesListBaseResponse?> {
        val response = apiServiceGenerator.processCallWithError {
            movieService.getUpComingMovies()
        }

        return ResourceBuilder<APIMoviesListBaseResponse>().build(response)
    }
}