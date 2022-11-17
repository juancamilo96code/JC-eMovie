package com.jc.collantes.emovie.data.mappers

import com.jc.collantes.emovie.data.model.api.APIMovieVideo
import org.junit.Test

class APIMovieVideosToMovieVideosMapperImplTest{

    @Test
    fun mapAPIMovieVideosToMovieVideos(){
        val apiMovieVideosToMovieVideosMapper = APIMovieVideosToMovieVideosMapperImpl()
        val apiMovieVideo = APIMovieVideo("1",null,null,null,null,null,null,null,null,null)
        val movieVideo = apiMovieVideosToMovieVideosMapper.map(apiMovieVideo)
        assert(apiMovieVideo.id == movieVideo?.id)
    }
}