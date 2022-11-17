package com.jc.collantes.emovie.data.mappers

import com.jc.collantes.emovie.data.model.database.entity.Movie
import com.jc.collantes.emovie.data.model.ui.SimpleMovieItem

class MovieDBToSimpleMovieMapperImpl:MovieDBToSimpleMovieMapper {
    override fun map(input: Movie): SimpleMovieItem {
        return SimpleMovieItem(
            input.id,
            name = input.title,
            imagePath = input.posterPath,
            releaseDate = input.releaseDate,
            originalLanguage = input.originalLanguage
        )
    }
}