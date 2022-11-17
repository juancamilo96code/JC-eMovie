package com.jc.collantes.emovie.data.service.api

import com.jc.collantes.emovie.data.model.api.APIMovieDetail
import com.jc.collantes.emovie.data.model.api.APIMovieVideosResponse
import com.jc.collantes.emovie.data.model.api.APIMoviesListBaseResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieService {

    @GET("upcoming")
    suspend fun getUpComingMovies():Response<APIMoviesListBaseResponse?>

    @GET("top_rated")
    suspend fun getTopRatedMovies():Response<APIMoviesListBaseResponse?>

    @GET("{movie_id}")
    suspend fun getMovieDetails(@Path("movie_id") movieId : Long):Response<APIMovieDetail>

    @GET("{movie_id}/videos")
    suspend fun getMovieVideos(@Path("movie_id") movieId : Long):Response<APIMovieVideosResponse?>

}