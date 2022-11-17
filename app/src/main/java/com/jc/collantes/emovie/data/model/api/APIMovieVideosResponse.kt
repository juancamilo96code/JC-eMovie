package com.jc.collantes.emovie.data.model.api

import com.google.gson.annotations.SerializedName

data class APIMovieVideosResponse(
    @SerializedName("id")
    val id : Long,
    @SerializedName("results")
    val results : List<APIMovieVideo>?
)

data class APIMovieVideo(
    @SerializedName("id")
    val id : String?,
    @SerializedName("iso_639_1")
    val iso6391 : String?,
    @SerializedName("iso_3166_1")
    val iso31661 : String?,
    @SerializedName("name")
    val name : String?,
    @SerializedName("key")
    val key : String?,
    @SerializedName("site")
    val site : String?,
    @SerializedName("size")
    val size : Long?,
    @SerializedName("type")
    val type : String?,
    @SerializedName("official")
    val official : Boolean?,
    @SerializedName("published_at")
    val publishedAt : String?,
)
