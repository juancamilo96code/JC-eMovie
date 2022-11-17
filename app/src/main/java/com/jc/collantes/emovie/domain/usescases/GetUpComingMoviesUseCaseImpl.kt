package com.jc.collantes.emovie.domain.usescases

import com.google.gson.JsonObject
import com.jc.collantes.emovie.data.datasource.UpcomingMoviesDataSourceImpl
import com.jc.collantes.emovie.data.model.Resource
import com.jc.collantes.emovie.data.model.ui.SimpleMovieItem
import com.jc.collantes.emovie.data.repositories.UpcomingMoviesRepository
import com.jc.collantes.emovie.data.repositories.UpcomingMoviesRepositoryImpl
import kotlinx.coroutines.Dispatchers

class GetUpComingMoviesUseCaseImpl(
    private val upcomingMoviesRepository: UpcomingMoviesRepository
): GetUpComingMoviesUseCase {

    lateinit var moviesResource : Resource<List<SimpleMovieItem>>

    override suspend fun invoke(
        filterYear: String?,
        filterLanguage: String?
    ): Resource<List<SimpleMovieItem>> {
        upcomingMoviesRepository.getUpcomingMovies().collect{
            moviesResource = it
        }
        return moviesResource
    }
}