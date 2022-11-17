package com.jc.collantes.emovie.data.model.api

import com.google.gson.annotations.SerializedName

data class APIMovie(
    @SerializedName("id")
    val id :Long,
    @SerializedName("adult")
    val adult : Boolean?,
    @SerializedName("backdrop_path")
    val backdropPath : String?,
    @SerializedName("original_language")
    val originalLanguage : String?,
    @SerializedName("original_title")
    val originalTitle : String?,
    @SerializedName("genre_ids")
    val genreIds : List<Int>?,
    @SerializedName("overview")
    val overview : String?,
    @SerializedName("popularity")
    val popularity : Float?,
    @SerializedName("poster_path")
    val posterPath : String?,
    @SerializedName("release_date")
    val releaseDate : String?,
    @SerializedName("title")
    val title : String?,
    @SerializedName("video")
    val video : Boolean?,
    @SerializedName("vote_average")
    val voteAverage : Float?,
    @SerializedName("vote_count")
    val vote_count : Float?
)

