package com.jc.collantes.emovie.domain.usescases

import com.jc.collantes.emovie.data.model.Resource
import com.jc.collantes.emovie.data.model.ui.SimpleMovieItem
import com.jc.collantes.emovie.data.repositories.TopRatedMoviesRepository
import com.jc.collantes.emovie.utils.Utils

class GetRecommendedMoviesFilteredUseCaseImpl(
    private val topRatedMoviesRepository: TopRatedMoviesRepository
) : GetRecommendedMoviesFilteredUseCase {

    private lateinit var moviesResource: Resource<List<SimpleMovieItem>>

    override suspend fun invoke(
        filterYear: String?,
        filterLanguage: String?
    ): Resource<List<SimpleMovieItem>> {
        topRatedMoviesRepository.getTopRatedMovies().collect {
            moviesResource = it
        }
        moviesResource.data = moviesResource.data?.filter {
            ((filterLanguage == null && filterYear == null)
                    || (filterLanguage == null && filterYear == it.releaseDate?.let { it1 -> Utils.getYearOfDate(it1) })
                    || (filterYear == null && filterLanguage == it.originalLanguage)
                    || (filterLanguage != null && filterYear != null && filterYear == it.releaseDate?.let { it1 -> Utils.getYearOfDate(it1) } && filterLanguage == it.originalLanguage ))
        }
        if(moviesResource.data != null){
            if (moviesResource.data?.size!! > 6){
                moviesResource.data = moviesResource.data?.subList(0,6)
            }
        }
        return moviesResource
    }
}