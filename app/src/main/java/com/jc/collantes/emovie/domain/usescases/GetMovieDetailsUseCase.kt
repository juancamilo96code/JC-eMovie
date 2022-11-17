package com.jc.collantes.emovie.domain.usescases

import com.jc.collantes.emovie.data.model.Resource
import com.jc.collantes.emovie.data.model.ui.MovieDetails
import com.jc.collantes.emovie.data.model.ui.SimpleMovieItem

interface GetMovieDetailsUseCase {

    suspend operator fun invoke(id: Long): Resource<MovieDetails?>

}