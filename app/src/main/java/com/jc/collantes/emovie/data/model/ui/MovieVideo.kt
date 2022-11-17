package com.jc.collantes.emovie.data.model.ui

import com.google.gson.annotations.SerializedName

data class MovieVideo(
    @SerializedName("id")
    val id : String? = null,
    @SerializedName("name")
    val name : String? = null,
    @SerializedName("key")
    val key : String? = null,
    @SerializedName("site")
    val site : String? = null,
    @SerializedName("type")
    val type : String? = null,
    @SerializedName("official")
    val official : Boolean? = false,
)
