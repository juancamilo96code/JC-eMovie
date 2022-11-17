package com.jc.collantes.emovie.data.repositories

import com.jc.collantes.emovie.data.datasource.TopRatedMoviesDataSource
import com.jc.collantes.emovie.data.datasource.TopRatedMoviesDataSourceImpl
import com.jc.collantes.emovie.data.mappers.APIMovieToMovieDBMapper
import com.jc.collantes.emovie.data.model.Resource
import com.jc.collantes.emovie.data.model.ui.SimpleMovieItem
import com.jc.collantes.emovie.data.mappers.APIMovieToSimpleMovieItemMapper
import com.jc.collantes.emovie.data.mappers.APIMovieToSimpleMovieItemMapperImpl
import com.jc.collantes.emovie.data.mappers.MovieDBToSimpleMovieMapper
import com.jc.collantes.emovie.data.model.api.APIMoviesListBaseResponse
import com.jc.collantes.emovie.data.model.database.entity.TopRatedMovie
import com.jc.collantes.emovie.data.service.local.MovieDao
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlin.coroutines.CoroutineContext

class TopRatedMoviesRepositoryImpl(
    private val topRatedMoviesDataSource: TopRatedMoviesDataSource,
    private val apiMovieToSimpleMovieItemMapper: APIMovieToSimpleMovieItemMapper,
    private val apiMovieToMovieDBMapper: APIMovieToMovieDBMapper,
    private val movieDBToSimpleMovieMapper : MovieDBToSimpleMovieMapper,
    private val movieDao: MovieDao,
    private val ioDispatcher : CoroutineContext
): TopRatedMoviesRepository {
    override suspend fun getTopRatedMovies(): Flow<Resource<List<SimpleMovieItem>>> {
        return flow {
            val response = topRatedMoviesDataSource.getTopRatedMovies()
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
                val topRatedListDB = movieDao.getTopRatedMovies()
                if (topRatedListDB != null){
                    val topRatedList = topRatedListDB.map {
                        movieDBToSimpleMovieMapper.map(it)
                    }
                    emit(Resource.Success(topRatedList))
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
        val topRatedList = databaseList?.map {
            TopRatedMovie(it.id)
        }
        movieDao.deleteTopRatedMovies()
        topRatedList?.let { movieDao.addTopRatedMovies(it) }
    }

}