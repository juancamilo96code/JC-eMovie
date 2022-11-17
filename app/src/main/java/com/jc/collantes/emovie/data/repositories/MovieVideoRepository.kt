package com.jc.collantes.emovie.data.repositories

import com.jc.collantes.emovie.data.model.Resource
import com.jc.collantes.emovie.data.model.api.APIMovieVideosResponse
import com.jc.collantes.emovie.data.model.ui.MovieVideo
import kotlinx.coroutines.flow.Flow

interface MovieVideoRepository {

    suspend fun getMovieVideos(
        movieId: Long
    ) : Flow<Resource<List<MovieVideo?>>>

}