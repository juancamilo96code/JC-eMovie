package com.jc.collantes.emovie.data.model.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TopRatedMovie (
    @PrimaryKey(autoGenerate = false)
    val id: Long
)