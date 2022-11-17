package com.jc.collantes.emovie.data.repositories

import com.jc.collantes.emovie.data.datasource.UpcomingMoviesDataSource
import com.jc.collantes.emovie.data.datasource.UpcomingMoviesDataSourceImpl
import com.jc.collantes.emovie.data.mappers.APIMovieToMovieDBMapper
import com.jc.collantes.emovie.data.model.Resource
import com.jc.collantes.emovie.data.model.ui.SimpleMovieItem
import com.jc.collantes.emovie.data.mappers.APIMovieToSimpleMovieItemMapper
import com.jc.collantes.emovie.data.mappers.APIMovieToSimpleMovieItemMapperImpl
import com.jc.collantes.emovie.data.mappers.MovieDBToSimpleMovieMapper
import com.jc.collantes.emovie.data.model.api.APIMoviesListBaseResponse
import com.jc.collantes.emovie.data.model.database.entity.TopRatedMovie
import com.jc.collantes.emovie.data.model.database.entity.UpcomingMovie
import com.jc.collantes.emovie.data.service.local.MovieDao
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlin.coroutines.CoroutineContext


class UpcomingMoviesRepositoryImpl(
    private val moviesUpcomingMoviesDataSource: UpcomingMoviesDataSource,
    private val apiMovieToSimpleMovieItemMapper: APIMovieToSimpleMovieItemMapper,
    private val apiMovieToMovieDBMapper: APIMovieToMovieDBMapper,
    private val movieDBToSimpleMovieMapper : MovieDBToSimpleMovieMapper,
    private val movieDao: MovieDao,
    private val ioDispatcher :CoroutineContext
): UpcomingMoviesRepository {
    override suspend fun getUpcomingMovies(): Flow<Resource<List<SimpleMovieItem>>> {
        return flow {
            val response = moviesUpcomingMoviesDataSource.getUpcomingMovies()
            if (response is Resource.Success){
                coroutineScope {
                    async(ioDispatcher){
                        updateDataBase(response)
                    }
                }
                emit(Resource.Success(response.data?.results?.map {
                    apiMovieToSimpleMovieItemMapper.map(it)
                }))
            }else{
                val upcomingMoviesListDB = movieDao.getUpcomingMovies()
                if (upcomingMoviesListDB != null){
                    val upcomingMoviesList = upcomingMoviesListDB.map {
                        movieDBToSimpleMovieMapper.map(it)
                    }
                    emit(Resource.Success(upcomingMoviesList))
                }else{
                    emit(
                        Resource.GenericDataError(
                            response.errorCode,
                            response.errorMessage
                        ))
                }
            }
        }.flowOn(ioDispatcher)
    }

    private suspend fun updateDataBase(response: Resource.Success<APIMoviesListBaseResponse?>) {
        val databaseList = response.data?.results?.map {
            apiMovieToMovieDBMapper.map(it)
        }
        movieDao.upsertMovies(databaseList)
        val upcomingMoviesList = databaseList?.map {
            UpcomingMovie(it.id)
        }
        movieDao.deleteTopRatedMovies()
        upcomingMoviesList?.let { movieDao.addUpcomingMovies(it) }
    }
}