package com.jc.collantes.emovie.data.service.local

import androidx.room.*
import com.jc.collantes.emovie.data.model.database.entity.Movie
import com.jc.collantes.emovie.data.model.database.entity.TopRatedMovie
import com.jc.collantes.emovie.data.model.database.entity.UpcomingMovie

@Dao
interface MovieDao {

    @Query("SELECT * FROM Movie WHERE id = :id")
    suspend fun getMovieDetails(id: Long): Movie?

    @Query("SELECT * FROM TopRatedMovie INNER JOIN Movie ON TopRatedMovie.id = Movie.id ORDER BY Movie.voteAverage DESC")
    suspend fun getTopRatedMovies(): List<Movie>?

    @Query("SELECT * FROM UpcomingMovie INNER JOIN Movie ON UpcomingMovie.id = Movie.id")
    suspend fun getUpcomingMovies(): List<Movie>?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(entities: Movie)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun update(entities: Movie)

    @Transaction
    suspend fun upsertMovie(movie: Movie){
        insert(movie)
        update(movie)
    }

    @Query("SELECT * FROM Movie")
    suspend fun getMovieDetailsList(): List<Movie>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUpcomingMovies(upcomingMovies: List<UpcomingMovie>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTopRatedMovies(topRatedMovies: List<TopRatedMovie>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMovieList(entities: List<Movie?>?)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun updateMovieList(entities: List<Movie?>?)

    @Transaction
    suspend fun upsertMovies(movies: List<Movie?>?){
        insertMovieList(movies)
        updateMovieList(movies)
    }

    @Query("DELETE FROM TopRatedMovie")
    suspend fun deleteTopRatedMovies()

    @Query("DELETE FROM UpcomingMovie")
    suspend fun deleteUpcomingMovies()

}