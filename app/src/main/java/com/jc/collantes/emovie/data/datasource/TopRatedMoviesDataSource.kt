package com.jc.collantes.emovie.data.datasource

import com.jc.collantes.emovie.data.model.Resource
import com.jc.collantes.emovie.data.model.api.APIMoviesListBaseResponse

interface TopRatedMoviesDataSource {

    suspend fun getTopRatedMovies(): Resource<APIMoviesListBaseResponse?>

}