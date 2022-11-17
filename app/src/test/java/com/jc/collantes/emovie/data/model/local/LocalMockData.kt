package com.jc.collantes.emovie.data.model.local

import com.google.gson.Gson
import com.jc.collantes.emovie.data.model.api.APIMovieVideo
import com.jc.collantes.emovie.data.model.database.entity.Movie

object LocalMockData {

    fun getFakeMovieDetail(): Movie {
        return Movie(1,null,null,null,null,null,null,
            null,null,null,null,null,null,null)
    }

    fun getFakeMovieDetailEdited(): Movie {
        return Movie(1,"MovieDetail",null,null,null,null,null,
            null,null,null,null,null,null, Gson().toJson(listOf<APIMovieVideo>()))
    }

    fun getFakeMovieDetailList(): List<Movie>{
        return listOf(
            Movie(1,null,null,null,null,null,null,
                null,null,null,null,null,null,null),
            Movie(2,null,null,null,null,null,null,
                null,null,null,null,null,null,null),
            Movie(3,null,null,null,null,null,null,
                null,null,null,null,null,null,null),
            Movie(4,null,null,null,null,null,null,
                null,null,null,null,null,null,null),
            Movie(5,null,null,null,null,null,null,
                null,null,null,null,null,null,null),
        )
    }

    fun getFakeMovieDetailEditedlList(): List<Movie>{
        return listOf(
            Movie(1,"MovieDetail",null,null,null,null,null,
                null,null,null,null,null,null,null),
            Movie(2,"MovieDetail",null,null,null,null,null,
                null,null,null,null,null,null,null),
            Movie(3,"MovieDetail",null,null,null,null,null,
                null,null,null,null,null,null,null),
            Movie(4,"MovieDetail",null,null,null,null,null,
                null,null,null,null,null,null,null),
            Movie(5,"MovieDetail",null,null,null,null,null,
                null,null,null,null,null,null,null),
        )
    }

    fun getFakeIdsList(): List<Long>{
        return listOf(1,2,3,4,5)
    }

}