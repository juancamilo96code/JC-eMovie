package com.jc.collantes.emovie.data.mappers

import com.jc.collantes.emovie.data.model.database.entity.Movie
import com.jc.collantes.emovie.data.model.ui.SimpleMovieItem

interface MovieDBToSimpleMovieMapper: GenericMapper<Movie,SimpleMovieItem> {
}