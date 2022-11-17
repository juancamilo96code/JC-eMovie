package com.jc.collantes.emovie.data.repositories

import com.jc.collantes.emovie.data.model.Resource
import com.jc.collantes.emovie.data.model.api.APIMovieDetail
import com.jc.collantes.emovie.data.model.ui.MovieDetails
import kotlinx.coroutines.flow.Flow

interface MovieDetailsRepository {

    suspend fun getMovieDetails(
        id: Long
    ) : Flow<Resource<MovieDetails?>>

}