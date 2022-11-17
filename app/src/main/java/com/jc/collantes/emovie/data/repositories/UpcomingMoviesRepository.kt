package com.jc.collantes.emovie.data.repositories

import com.google.gson.JsonObject
import com.jc.collantes.emovie.data.model.Resource
import com.jc.collantes.emovie.data.model.api.APIMoviesListBaseResponse
import com.jc.collantes.emovie.data.model.ui.SimpleMovieItem
import kotlinx.coroutines.flow.Flow

interface UpcomingMoviesRepository {

    suspend fun getUpcomingMovies() : Flow<Resource<List<SimpleMovieItem>>>

}