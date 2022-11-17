package com.jc.collantes.emovie.data.mappers

import com.jc.collantes.emovie.data.model.api.APIMovie
import com.jc.collantes.emovie.data.model.ui.SimpleMovieItem

class APIMovieToSimpleMovieItemMapperImpl : APIMovieToSimpleMovieItemMapper {

    override fun map(input: APIMovie): SimpleMovieItem {
        return SimpleMovieItem(
            input.id,
            name = input.title,
            imagePath = input.posterPath,
            releaseDate = input.releaseDate,
            originalLanguage = input.originalLanguage
        )
    }

}