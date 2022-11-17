package com.jc.collantes.emovie.data.service.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jc.collantes.emovie.data.model.database.entity.Movie
import com.jc.collantes.emovie.data.model.database.entity.TopRatedMovie
import com.jc.collantes.emovie.data.model.database.entity.UpcomingMovie

@Database(entities = [Movie::class, TopRatedMovie::class, UpcomingMovie::class], version = 2)
abstract class MovieDataBase :RoomDatabase() {
    abstract val movieDao: MovieDao
}