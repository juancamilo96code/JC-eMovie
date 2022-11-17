package com.jc.collantes.emovie.data.model.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Movie (
    @PrimaryKey(autoGenerate = false)
    val id: Long,
    val originalTitle: String?,
    val title: String?,
    val tagline: String?,
    val overview: String?,
    val posterPath: String?,
    val releaseDate: String?,
    val originalLanguage: String?,
    val backdropPath: String?,
    val video: Boolean?,
    val voteAverage: Float?,
    val genres: String?,
    val spokenLanguages: String?,
    var videosData : String? = null
)