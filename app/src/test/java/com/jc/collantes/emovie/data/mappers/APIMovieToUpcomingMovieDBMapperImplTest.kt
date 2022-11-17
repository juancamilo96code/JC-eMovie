package com.jc.collantes.emovie.data.mappers

import com.jc.collantes.emovie.data.model.api.APIMovie
import org.junit.Test

class APIMovieToUpcomingMovieDBMapperImplTest{

    @Test
    fun mapAPIMovieToUpcomingMovieDB(){
        val apiAPIMovieToUpcomingMovieDBMapper = APIMovieToUpcomingMovieDBMapperImpl()
        val apiMovie = APIMovie(
            234, null, null, null,
            null, null, null, null, null, null,
            null, null, null, null
        )
        val movieDB = apiAPIMovieToUpcomingMovieDBMapper.map(apiMovie)
        assert(apiMovie.id == movieDB.id)
    }
}