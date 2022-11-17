package com.jc.collantes.emovie.data.mappers

import com.jc.collantes.emovie.data.model.api.APIMovie
import org.junit.Test

class APIMovieToTopRatedMovieDBMapperImplTest{

    @Test
    fun mapAPIMovieDetailsToMovieDB(){
        val apiMovieToTopRatedMovieDBMapper = APIMovieToTopRatedMovieDBMapperImpl()
        val apiMovie = APIMovie(
            234, null, null, null,
            null, null, null, null, null, null,
            null, null, null, null
        )
        val movieDB = apiMovieToTopRatedMovieDBMapper.map(apiMovie)
        assert(apiMovie.id == movieDB.id)
    }
}