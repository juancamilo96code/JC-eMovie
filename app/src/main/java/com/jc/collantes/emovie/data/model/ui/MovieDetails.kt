package com.jc.collantes.emovie.data.model.ui

import com.jc.collantes.emovie.data.model.api.APIGenre
import com.jc.collantes.emovie.data.model.api.APISpokenLanguages

data class MovieDetails(
    val singleMovieItemData: SimpleMovieItem,
    val originalLanguage : String?,
    val originalTitle : String?,
    val overview : String?,
    val video : Boolean?,
    val voteAverage : Float?,
    val genres : List<APIGenre>?,
    val spokenLanguages : List<APISpokenLanguages>?,
    val tagline : String?,
    val releaseDate : String?
    )
