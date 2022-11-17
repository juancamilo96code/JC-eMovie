package com.jc.collantes.emovie.data.mappers

import com.jc.collantes.emovie.data.model.api.APIMovie
import com.jc.collantes.emovie.data.model.ui.SimpleMovieItem

interface APIMovieToSimpleMovieItemMapper : GenericMapper<APIMovie, SimpleMovieItem>