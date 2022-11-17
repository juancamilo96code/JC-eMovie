package com.jc.collantes.emovie.domain.di

import com.jc.collantes.emovie.data.mappers.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MappersModule {

    @Provides
    @Singleton
    fun provideAPIMovieDetailsToMovieDBMapper(): APIMovieDetailsToMovieDBMapper {
        return APIMovieDetailsToMovieDBMapperImpl()
    }


    @Provides
    @Singleton
    fun provideAPIMovieDetailsToMovieDetailsMapper(): APIMovieDetailsToMovieDetailsMapper {
        return APIMovieDetailsToMovieDetailsMapperImpl()
    }

    @Provides
    @Singleton
    fun provideAPIMovieToMovieDBMapper(): APIMovieToMovieDBMapper {
        return APIMovieToMovieDBMapperImpl()
    }

    @Provides
    @Singleton
    fun provideAPIMovieToSimpleMovieItemMapper(): APIMovieToSimpleMovieItemMapper {
        return APIMovieToSimpleMovieItemMapperImpl()
    }

    @Provides
    @Singleton
    fun provideAPIMovieToTopRatedMovieDBMapper(): APIMovieToTopRatedMovieDBMapper {
        return APIMovieToTopRatedMovieDBMapperImpl()
    }

    @Provides
    @Singleton
    fun provideAPIMovieToUpcomingMovieDBMapper(): APIMovieToUpcomingMovieDBMapper {
        return APIMovieToUpcomingMovieDBMapperImpl()
    }

    @Provides
    @Singleton
    fun provideAPIMovieVideosToMovieVideosMapper(): APIMovieVideosToMovieVideosMapper {
        return APIMovieVideosToMovieVideosMapperImpl()
    }

    @Provides
    @Singleton
    fun provideMovieDBToMovieDetailsMapper(): MovieDBToMovieDetailsMapper {
        return MovieDBToMovieDetailsMapperImpl()
    }

    @Provides
    @Singleton
    fun provideMovieDBToSimpleMovieMapper(): MovieDBToSimpleMovieMapper {
        return MovieDBToSimpleMovieMapperImpl()
    }

}