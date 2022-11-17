package com.jc.collantes.emovie.data.mappers

import com.google.gson.annotations.SerializedName
import com.jc.collantes.emovie.data.model.api.APIMovieVideo
import com.jc.collantes.emovie.data.model.ui.MovieVideo

class APIMovieVideosToMovieVideosMapperImpl : APIMovieVideosToMovieVideosMapper {
    override fun map(input: APIMovieVideo?): MovieVideo? {
        return if (input != null) {
            MovieVideo(
                id= input.id,
                name = input.name,
                key = input.key,
                site = input.site,
                type = input.type,
                official = input.official
            )
        }else null
    }
}