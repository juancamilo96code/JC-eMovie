package com.jc.collantes.emovie.data.datasource

import com.jc.collantes.emovie.data.model.Resource
import com.jc.collantes.emovie.data.model.api.APIMovieVideosResponse
import com.jc.collantes.emovie.data.service.api.ApiServiceGenerator
import com.jc.collantes.emovie.data.service.api.MovieService
import com.jc.collantes.emovie.utils.ResourceBuilder

class MovieVideosDataSourceImpl(
    private val apiServiceGenerator: ApiServiceGenerator
) : MovieVideosDataSource {

    private val movieService = apiServiceGenerator.createService(MovieService::class.java)

    override suspend fun getMovieVideos(movieId: Long): Resource<APIMovieVideosResponse?> {

        val response = apiServiceGenerator.processCallWithError {
            movieService.getMovieVideos(movieId)
        }

        return ResourceBuilder<APIMovieVideosResponse>().build(response)
    }


}