package com.jc.collantes.emovie.data.datasource

import com.jc.collantes.emovie.data.model.Resource
import com.jc.collantes.emovie.data.model.api.APIMoviesListBaseResponse

interface UpcomingMoviesDataSource {

    suspend fun getUpcomingMovies(): Resource<APIMoviesListBaseResponse?>

}