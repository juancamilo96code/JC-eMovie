package com.jc.collantes.emovie.data.model.remote

import com.google.gson.annotations.SerializedName
import com.jc.collantes.emovie.data.model.Resource
import com.jc.collantes.emovie.data.model.api.APIMovie
import com.jc.collantes.emovie.data.model.api.APIMovieDetail
import com.jc.collantes.emovie.data.model.api.APIMovieVideosResponse
import com.jc.collantes.emovie.data.model.api.APIMoviesListBaseResponse

object RemoteMockData {

    fun getMovieDetail(): Resource<APIMovieDetail?>{
        return Resource.Success(APIMovieDetail(1, originalTitle = "Original Title"))
    }

    fun getMovieVideos(): Resource<APIMovieVideosResponse?>{
        return Resource.Success(
            APIMovieVideosResponse(
                1,
                listOf(

                )
            )
        )
    }


    fun getMovieList(): Resource<APIMoviesListBaseResponse?>{
        return Resource.Success(
            APIMoviesListBaseResponse(
                1,5,40,
                listOf(
                    APIMovie(1,null,null,null,null,
                        null,null,null,null,null,
                        null,null,null,null)
                )
            )
        )
    }
}