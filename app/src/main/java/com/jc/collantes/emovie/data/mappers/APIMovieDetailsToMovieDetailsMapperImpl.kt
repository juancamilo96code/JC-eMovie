package com.jc.collantes.emovie.data.mappers

import com.jc.collantes.emovie.data.model.api.APIMovieDetail
import com.jc.collantes.emovie.data.model.ui.MovieDetails
import com.jc.collantes.emovie.data.model.ui.SimpleMovieItem

class APIMovieDetailsToMovieDetailsMapperImpl : APIMovieDetailsToMovieDetailsMapper {
    override fun map(input: APIMovieDetail?): MovieDetails? {
        if (input == null || input.id == -1L) {
            return null
        }
        return MovieDetails(
            SimpleMovieItem(
                input.id,
                input.title,
                input.posterPath
            ),
            input.originalLanguage,
            input.originalTitle,
            input.overview,
            input.video,
            input.voteAverage,
            input.genres,
            input.spokenLanguages,
            input.tagline,
            input.releaseDate
        )
    }
}