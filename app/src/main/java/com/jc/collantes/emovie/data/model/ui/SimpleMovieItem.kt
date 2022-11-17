package com.jc.collantes.emovie.data.model.ui

data class SimpleMovieItem(
    val id: Long,
    val name: String?,
    val imagePath: String?,
    val genres: String? = null,
    val releaseDate: String? = null,
    val originalLanguage: String? = null
)
