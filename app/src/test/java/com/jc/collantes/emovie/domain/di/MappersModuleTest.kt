package com.jc.collantes.emovie.domain.di

import com.jc.collantes.emovie.data.mappers.*
import io.mockk.MockKAnnotations
import org.junit.Before
import org.junit.Test

class MappersModuleTest {

    private lateinit var mappersModule: MappersModule

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        mappersModule = MappersModule
    }

    @Test
    fun provideAPIMovieDetailsToMovieDBMapper() {
        val apiMovieDetailsToMovieDBMapper = mappersModule.provideAPIMovieDetailsToMovieDBMapper()
        assert(apiMovieDetailsToMovieDBMapper is APIMovieDetailsToMovieDBMapperImpl)
    }

    @Test
    fun provideAPIMovieDetailsToMovieDetailsMapper() {
        val apiMovieDetailsToMovieDetailsMapper = mappersModule.provideAPIMovieDetailsToMovieDetailsMapper()
        assert(apiMovieDetailsToMovieDetailsMapper is APIMovieDetailsToMovieDetailsMapperImpl)
    }

    @Test
    fun provideAPIMovieToMovieDBMapper() {
        val apiMovieToMovieDBMapper = mappersModule.provideAPIMovieToMovieDBMapper()
        assert(apiMovieToMovieDBMapper is APIMovieToMovieDBMapperImpl)
    }

    @Test
    fun provideAPIMovieToSimpleMovieItemMapper() {
        val apiMovieToSimpleMovieItemMapper = mappersModule.provideAPIMovieToSimpleMovieItemMapper()
        assert(apiMovieToSimpleMovieItemMapper is APIMovieToSimpleMovieItemMapperImpl)
    }

    @Test
    fun provideAPIMovieToTopRatedMovieDBMapper() {
        val apiMovieToTopRatedMovieDBMapper = mappersModule.provideAPIMovieToTopRatedMovieDBMapper()
        assert(apiMovieToTopRatedMovieDBMapper is APIMovieToTopRatedMovieDBMapperImpl)
    }

    @Test
    fun provideAPIMovieToUpcomingMovieDBMapper() {
        val apiMovieToUpcomingMovieDBMapper = mappersModule.provideAPIMovieToUpcomingMovieDBMapper()
        assert(apiMovieToUpcomingMovieDBMapper is APIMovieToUpcomingMovieDBMapperImpl)
    }

    @Test
    fun provideAPIMovieVideosToMovieVideosMapper() {
        val apiMovieVideosToMovieVideosMapper = mappersModule.provideAPIMovieVideosToMovieVideosMapper()
        assert(apiMovieVideosToMovieVideosMapper is APIMovieVideosToMovieVideosMapperImpl)
    }

    @Test
    fun provideMovieDBToMovieDetailsMapper() {
        val movieDBToMovieDetailsMapper = mappersModule.provideMovieDBToMovieDetailsMapper()
        assert(movieDBToMovieDetailsMapper is MovieDBToMovieDetailsMapperImpl)
    }

    @Test
    fun provideMovieDBToSimpleMovieMapper() {
        val movieDBToSimpleMovieMapper = mappersModule.provideMovieDBToSimpleMovieMapper()
        assert(movieDBToSimpleMovieMapper is MovieDBToSimpleMovieMapperImpl)
    }
}