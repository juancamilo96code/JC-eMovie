package com.jc.collantes.emovie.data.mappers

import com.jc.collantes.emovie.data.model.database.entity.Movie
import org.junit.Test

class MovieDBToSimpleMovieMapperImplTest{

    @Test
    fun mapMovieDBToSimpleMovie(){
        val movieDBToSimpleMovieMapper = MovieDBToSimpleMovieMapperImpl()
        val movieDB = Movie(1,"Movie Details",null,null,null,null,null,
            null,null,null,null,null,null,null)
        val movie = movieDBToSimpleMovieMapper.map(movieDB)
        assert(movieDB.id == movie.id)
    }
}