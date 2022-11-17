package com.jc.collantes.emovie.data.datasource

import com.jc.collantes.emovie.data.model.Resource
import com.jc.collantes.emovie.data.model.api.APIMovieDetail

interface MovieDetailsDataSource {

    suspend fun getMovieDetails(
        id: Long
    ): Resource<APIMovieDetail?>

}