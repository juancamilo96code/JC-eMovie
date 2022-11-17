package com.jc.collantes.emovie.domain.usescases

import com.jc.collantes.emovie.data.model.Resource
import com.jc.collantes.emovie.data.model.ui.MovieVideo

interface GetMovieTrailerUseCase {

    suspend operator fun invoke(movieId: Long): Resource<MovieVideo?>

}