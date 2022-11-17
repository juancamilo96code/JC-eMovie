package com.jc.collantes.emovie.domain.usescases

import com.jc.collantes.emovie.data.model.Resource
import com.jc.collantes.emovie.data.model.ui.MovieDetails
import com.jc.collantes.emovie.data.repositories.MovieDetailsRepository

class GetMovieDetailsUseCaseImpl(
    private val moviesDetailsRepository: MovieDetailsRepository
):GetMovieDetailsUseCase {

    lateinit var movieResource : Resource<MovieDetails?>

    override suspend fun invoke(id: Long): Resource<MovieDetails?> {
        moviesDetailsRepository.getMovieDetails(id).collect{
            movieResource = it
        }
        return movieResource
    }
}