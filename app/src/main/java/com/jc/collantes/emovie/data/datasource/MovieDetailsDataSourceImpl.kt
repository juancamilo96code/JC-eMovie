package com.jc.collantes.emovie.data.datasource

import com.jc.collantes.emovie.data.model.Resource
import com.jc.collantes.emovie.data.model.api.APIMovieDetail
import com.jc.collantes.emovie.data.service.api.ApiServiceGenerator
import com.jc.collantes.emovie.data.service.api.MovieService
import com.jc.collantes.emovie.utils.ResourceBuilder

class MovieDetailsDataSourceImpl(
    private val apiServiceGenerator: ApiServiceGenerator
) : MovieDetailsDataSource {

    private val movieService = apiServiceGenerator.createService(MovieService::class.java)

    override suspend fun getMovieDetails(id: Long): Resource<APIMovieDetail?> {

        val response = apiServiceGenerator.processCallWithError {
            movieService.getMovieDetails(id)
        }
        return ResourceBuilder<APIMovieDetail>().build(response)
    }
}