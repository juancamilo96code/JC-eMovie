package com.jc.collantes.emovie.data.mappers

import com.google.gson.Gson
import com.jc.collantes.emovie.data.model.api.APIMovieDetail
import com.jc.collantes.emovie.data.model.database.entity.Movie

class APIMovieDetailsToMovieDBMapperImpl : APIMovieDetailsToMovieDBMapper {
    override fun map(input: APIMovieDetail): Movie {
        val gson = Gson()
        return Movie(
            id = input.id,
            originalTitle = input.originalTitle,
            title = input.title,
            tagline = input.tagline,
            overview = input.overview,
            posterPath = input.posterPath,
            releaseDate = input.releaseDate,
            originalLanguage = input.originalLanguage,
            backdropPath = input.backdropPath,
            video = input.video,
            voteAverage = input.voteAverage,
            genres = if (input.genres != null){gson.toJson(input.genres)}else{null},
            spokenLanguages = if (input.spokenLanguages != null){gson.toJson(input.spokenLanguages)}else{null}
        )
    }
}