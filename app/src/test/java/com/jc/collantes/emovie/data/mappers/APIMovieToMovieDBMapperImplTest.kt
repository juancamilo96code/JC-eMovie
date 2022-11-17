package com.jc.collantes.emovie.data.mappers

import com.jc.collantes.emovie.data.model.api.APIMovie
import org.junit.Test

class APIMovieToMovieDBMapperImplTest {

    @Test
    fun mapAPIMovieToMovieDB() {
        val apiMovieToMovieDBMapper = APIMovieToMovieDBMapperImpl()
        val apiMovie = APIMovie(
            234, null, null, null,
            null, null, null, null, null, null,
            null, null, null, null
        )
        val movieDB = apiMovieToMovieDBMapper.map(apiMovie)
        assert(apiMovie.id == movieDB.id)
    }
}