package com.jc.collantes.emovie.data.service.local

import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Test

class MovieDataBaseTest{

    @MockK
    lateinit var dataBase: MovieDataBase

    @MockK
    lateinit var movieDao: MovieDao

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
    }

    @Test
    fun checkMovieDao(){
        coEvery { dataBase.movieDao } returns movieDao

        MatcherAssert.assertThat(
            dataBase.movieDao,
            CoreMatchers.instanceOf(MovieDao::class.java)
        )
    }

}