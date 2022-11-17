package com.jc.collantes.emovie.data.mappers

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jc.collantes.emovie.data.model.api.APIGenre
import com.jc.collantes.emovie.data.model.api.APISpokenLanguages
import com.jc.collantes.emovie.data.model.database.entity.Movie
import com.jc.collantes.emovie.data.model.ui.MovieDetails
import com.jc.collantes.emovie.data.model.ui.SimpleMovieItem
import java.lang.reflect.Type

class MovieDBToMovieDetailsMapperImpl: MovieDBToMovieDetailsMapper {
    override fun map(input: Movie): MovieDetails {
        val gson = Gson()
        val listGenresClassObjects:Type = object :TypeToken<List<APIGenre>>(){}.type
        val listSpokenLanguagesClassObjects : Type = object :TypeToken<List<APISpokenLanguages>>(){}.type
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
            gson.fromJson(input.genres,listGenresClassObjects),
            gson.fromJson(input.spokenLanguages, listSpokenLanguagesClassObjects),
            input.tagline,
            input.releaseDate
        )
    }
}