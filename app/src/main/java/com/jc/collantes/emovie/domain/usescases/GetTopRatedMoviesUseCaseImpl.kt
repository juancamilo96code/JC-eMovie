package com.jc.collantes.emovie.domain.usescases

import com.jc.collantes.emovie.data.model.Resource
import com.jc.collantes.emovie.data.model.ui.SimpleMovieItem
import com.jc.collantes.emovie.data.repositories.TopRatedMoviesRepository

class GetTopRatedMoviesUseCaseImpl(
    private val topRatedMoviesRepository: TopRatedMoviesRepository
) : GetTopRatedMoviesUseCase{
    override suspend fun invoke(
        filterYear: String?,
        filterLanguage: String?
    ): Resource<List<SimpleMovieItem>> {

        lateinit var moviesResource : Resource<List<SimpleMovieItem>>

        topRatedMoviesRepository.getTopRatedMovies().collect{
            moviesResource = it
        }
        return moviesResource
    }
}