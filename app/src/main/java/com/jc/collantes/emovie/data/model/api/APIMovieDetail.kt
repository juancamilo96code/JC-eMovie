package com.jc.collantes.emovie.data.model.api

import com.google.gson.annotations.SerializedName

data class APIMovieDetail(
    @SerializedName("id")
    val id :Long = -1,
    @SerializedName("adult")
    val adult : Boolean? = null,
    @SerializedName("backdrop_path")
    val backdropPath : String? = null,
    @SerializedName("original_language")
    val originalLanguage : String? = null,
    @SerializedName("original_title")
    val originalTitle : String? = null,
    @SerializedName("overview")
    val overview : String? = null,
    @SerializedName("popularity")
    val popularity : Float? = null,
    @SerializedName("poster_path")
    val posterPath : String? = null,
    @SerializedName("release_date")
    val releaseDate : String? = null,
    @SerializedName("title")
    val title : String? = null,
    @SerializedName("video")
    val video : Boolean? = null,
    @SerializedName("vote_average")
    val voteAverage : Float? = null,
    @SerializedName("vote_count")
    val vote_count : Float? = null,
    @SerializedName("budget")
    val budget : Long? = null,
    @SerializedName("homepage")
    val homepage : String? = null,
    @SerializedName("genres")
    val genres : List<APIGenre>? = null,
    @SerializedName("spoken_languages")
    val spokenLanguages : List<APISpokenLanguages>? = null,
    @SerializedName("status")
    val status : String? = null,
    @SerializedName("tagline")
    val tagline : String? = null,
    @SerializedName("runtime")
    val runtime : Int? = null,
    @SerializedName("revenue")
    val revenue : Long? = null,
    @SerializedName("imdb_id")
    val imdbId : String? = null,
    @SerializedName("belongs_to_collection")
    val belongsToCollection : APIBelongToCollection? = null,
    @SerializedName("production_companies")
    val productionCompanies : List<ProductionCompany>? = null,
    @SerializedName("production_countries")
    val productionCountries : List<ProductionCountry>? = null
)

data class APIGenre(
    @SerializedName("id")
    val id :String,
    @SerializedName("name")
    val name : String?
)

data class APISpokenLanguages(
    @SerializedName("english_name")
    val english_name :String?,
    @SerializedName("iso_639_1")
    val nameISO : String?,
    @SerializedName("name")
    val name : String?
)

data class APIBelongToCollection(
    @SerializedName("id")
    val id :Long?,
    @SerializedName("name")
    val name :String?,
    @SerializedName("poster_path")
    val posterPath : String?,
    @SerializedName("backdrop_path")
    val backdropPath : String?
)

data class ProductionCompany(
    @SerializedName("id")
    val id :Long?,
    @SerializedName("logo_path")
    val logoPath :String?,
    @SerializedName("name")
    val name : String?,
    @SerializedName("origin_country")
    val originCountry : String?
)

data class ProductionCountry(
    @SerializedName("iso_3166_1")
    val iso :String?,
    @SerializedName("name")
    val name : String?
)


