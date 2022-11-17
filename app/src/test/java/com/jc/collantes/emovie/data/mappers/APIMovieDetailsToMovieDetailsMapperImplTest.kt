package com.jc.collantes.emovie.data.mappers

import com.jc.collantes.emovie.data.model.api.APIMovieDetail
import org.junit.Test

class APIMovieDetailsToMovieDetailsMapperImplTest{

    @Test
    fun mapAPIMovieDetailsToMovieDetails(){
        val apiMovieDetailsToMovieDetailsMapper = APIMovieDetailsToMovieDetailsMapperImpl()
        val apiMovieDetail = APIMovieDetail (234)
        val movieDetail = apiMovieDetailsToMovieDetailsMapper.map(apiMovieDetail)
        if (movieDetail != null) {
            assert(apiMovieDetail.id == movieDetail.singleMovieItemData.id)
        }
    }
}
