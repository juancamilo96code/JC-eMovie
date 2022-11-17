package com.jc.collantes.emovie.data.mappers

import com.jc.collantes.emovie.data.model.api.APIMovieDetail
import com.jc.collantes.emovie.data.model.database.entity.Movie

interface APIMovieDetailsToMovieDBMapper:GenericMapper<APIMovieDetail, Movie>