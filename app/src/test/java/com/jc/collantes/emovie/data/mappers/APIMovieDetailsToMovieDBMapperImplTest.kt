package com.jc.collantes.emovie.data.mappers


import com.jc.collantes.emovie.data.model.api.APIMovieDetail
import org.junit.Test

class APIMovieDetailsToMovieDBMapperImplTest{

    @Test
    fun mapAPIMovieDetailsToMovieDB(){
        val apiMovieDetailsToMovieDBMapper = APIMovieDetailsToMovieDBMapperImpl()
        val apiMovieDetail = APIMovieDetail (234)
        val movieDB = apiMovieDetailsToMovieDBMapper.map(apiMovieDetail)
        assert(apiMovieDetail.id == movieDB.id)
    }
}