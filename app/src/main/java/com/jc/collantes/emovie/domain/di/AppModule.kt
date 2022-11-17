package com.jc.collantes.emovie.domain.di


import android.app.Application
import android.content.Context
import androidx.room.Room
import com.jc.collantes.emovie.R
import com.jc.collantes.emovie.data.datasource.*
import com.jc.collantes.emovie.data.mappers.*
import com.jc.collantes.emovie.data.service.api.ApiServiceGenerator
import com.jc.collantes.emovie.data.service.local.MovieDao
import com.jc.collantes.emovie.data.service.local.MovieDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMovieApiServiceGenerator(@ApplicationContext appContext: Context): ApiServiceGenerator {
        return ApiServiceGenerator(
            appContext.getString(R.string.themoviedb_base_api_url),
            appContext.getString(R.string.themoviedb_api_key),
            appContext.getString(R.string.es_language_iso)
        )
    }

    @Provides
    @Singleton
    fun provideMovieDao(application: Application): MovieDao {
        val db = Room.databaseBuilder(
            application,
            MovieDataBase::class.java,
            "movies_db"
        ).build()
        return db.movieDao
    }

}