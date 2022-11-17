package com.jc.collantes.emovie.domain.di

import com.jc.collantes.emovie.data.datasource.*
import com.jc.collantes.emovie.data.service.api.ApiServiceGenerator
import io.mockk.MockKAnnotations
import org.junit.Before
import org.junit.Test

class DataSourceModuleTest {

    private lateinit var dataSourceModule : DataSourceModule
    private val apiServiceGenerator =ApiServiceGenerator(
    "https://api.themoviedb.org/3/movie/",
    "175a7356eb593bbb3031e5bdd8f99903",
    "es-CO"
    )

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        dataSourceModule = DataSourceModule
    }

    @Test
    fun provideMovieVideosDataSource() {
        val dataSource = dataSourceModule.provideMovieVideosDataSource(apiServiceGenerator)
        assert(dataSource is MovieVideosDataSourceImpl)
    }

    @Test
    fun provideTopRatedMoviesDataSource() {
        val dataSource = dataSourceModule.provideTopRatedMoviesDataSource(apiServiceGenerator)
        assert(dataSource is TopRatedMoviesDataSourceImpl)
    }

    @Test
    fun provideUpcomingMoviesDataSource() {
        val dataSource = dataSourceModule.provideUpcomingMoviesDataSource(apiServiceGenerator)
        assert(dataSource is UpcomingMoviesDataSourceImpl)
    }

    @Test
    fun provideMovieDetailsDataSource() {
        val dataSource = dataSourceModule.provideMovieDetailsDataSource(apiServiceGenerator)
        assert(dataSource is MovieDetailsDataSourceImpl)
    }
}