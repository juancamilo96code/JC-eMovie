package com.jc.collantes.emovie.data.mappers

import com.jc.collantes.emovie.data.model.api.APIMovie
import com.jc.collantes.emovie.data.model.database.entity.TopRatedMovie

class APIMovieToUpcomingMovieDBMapperImpl : APIMovieToUpcomingMovieDBMapper {
    override fun map(input: APIMovie): TopRatedMovie {
        return TopRatedMovie(input.id)
    }
}