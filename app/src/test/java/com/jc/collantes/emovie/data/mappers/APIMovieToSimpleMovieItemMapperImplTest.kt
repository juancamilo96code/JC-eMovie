package com.jc.collantes.emovie.data.mappers

import com.jc.collantes.emovie.data.model.api.APIMovie
import org.junit.Test

class APIMovieToSimpleMovieItemMapperImplTest{

    @Test
    fun mapAPIMovieToSimpleMovieItem(){
        val apiMovieToSimpleMovieItemMapper = APIMovieToSimpleMovieItemMapperImpl()
        val apiMovie = APIMovie(
            234, null, null, null,
            null, null, null, null, null, null,
            null, null, null, null
        )
        val movieDB = apiMovieToSimpleMovieItemMapper.map(apiMovie)
        assert(apiMovie.id == movieDB.id)
    }
}