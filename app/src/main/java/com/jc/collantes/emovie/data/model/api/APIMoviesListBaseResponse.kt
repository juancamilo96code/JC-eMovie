package com.jc.collantes.emovie.data.model.api

import com.google.gson.annotations.SerializedName

data class APIMoviesListBaseResponse(
    @SerializedName("page")
    val page :Int?,
    @SerializedName("total_pages")
    val totalPages: Int?,
    @SerializedName("total_results")
    val totalResults: Int?,
    @SerializedName("results")
    val results: List<APIMovie>,
)
