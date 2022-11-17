package com.jc.collantes.emovie.data.datasource

import com.jc.collantes.emovie.data.model.Resource
import com.jc.collantes.emovie.data.model.api.APIMovieVideosResponse

interface MovieVideosDataSource {

    suspend fun getMovieVideos(
        movieId: Long
    ): Resource<APIMovieVideosResponse?>

}