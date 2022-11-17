package com.jc.collantes.emovie.data.mappers

import com.jc.collantes.emovie.data.model.api.APIMovie
import com.jc.collantes.emovie.data.model.database.entity.Movie

interface APIMovieToMovieDBMapper : GenericMapper<APIMovie, Movie>