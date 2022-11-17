package com.jc.collantes.emovie.data.mappers

import com.jc.collantes.emovie.data.model.database.entity.Movie
import org.junit.Test

class MovieDBToMovieDetailsMapperImplTest{

    @Test
    fun mapMovieDBToMovieDetails(){
        val movieDBToMovieDetailsMapper = MovieDBToMovieDetailsMapperImpl()
        val movieDB = Movie(1,"Movie Details",null,null,null,null,null,
            null,null,null,null,null,null,null)
        val movieDetails = movieDBToMovieDetailsMapper.map(movieDB)
        assert(movieDB.originalTitle == movieDetails.originalTitle)
    }
}