package com.jc.collantes.emovie.data.mappers

import com.jc.collantes.emovie.data.model.api.APIMovie
import com.jc.collantes.emovie.data.model.database.entity.Movie

class APIMovieToMovieDBMapperImpl : APIMovieToMovieDBMapper {
    override fun map(input: APIMovie): Movie {
        return Movie(
            id = input.id,
            originalTitle = input.originalTitle,
            title = input.title,
            tagline = null,
            overview = input.overview,
            posterPath = input.posterPath,
            releaseDate = input.releaseDate,
            originalLanguage = input.originalLanguage,
            backdropPath = input.backdropPath,
            video = input.video,
            voteAverage = input.voteAverage,
            genres = null,
            spokenLanguages = null
        )
    }
}