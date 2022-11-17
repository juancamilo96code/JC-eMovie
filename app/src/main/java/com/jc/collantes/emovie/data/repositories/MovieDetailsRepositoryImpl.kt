package com.jc.collantes.emovie.data.repositories

import com.jc.collantes.emovie.data.datasource.MovieDetailsDataSource
import com.jc.collantes.emovie.data.mappers.APIMovieDetailsToMovieDBMapper
import com.jc.collantes.emovie.data.model.Resource
import com.jc.collantes.emovie.data.model.ui.MovieDetails
import com.jc.collantes.emovie.data.mappers.APIMovieDetailsToMovieDetailsMapper
import com.jc.collantes.emovie.data.mappers.MovieDBToMovieDetailsMapper
import com.jc.collantes.emovie.data.model.api.APIMovieDetail
import com.jc.collantes.emovie.data.service.local.MovieDao
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlin.coroutines.CoroutineContext


class MovieDetailsRepositoryImpl(
    private val moviesDetailsDataSource: MovieDetailsDataSource,
    private val apiMovieDetailsToMovieDetailsMapper: APIMovieDetailsToMovieDetailsMapper,
    private val apiMovieDetailsToMovieDBMapper: APIMovieDetailsToMovieDBMapper,
    private val movieDBToMovieDetailsMapper: MovieDBToMovieDetailsMapper,
    private val movieDao: MovieDao,
    private val ioDispatcher: CoroutineContext
) : MovieDetailsRepository {

    override suspend fun getMovieDetails(id: Long): Flow<Resource<MovieDetails?>> {
        return flow<Resource<MovieDetails?>> {
            val response = moviesDetailsDataSource.getMovieDetails(id)
            if (response is Resource.Success) {
                coroutineScope {
                    async(ioDispatcher) {
                        updateDataBase(response)
                    }
                }
                emit(Resource.Success(apiMovieDetailsToMovieDetailsMapper.map(response.data)))
            } else {
                val movieDB = movieDao.getMovieDetails(id)
                if (movieDB != null) {
                    emit(Resource.Success(movieDBToMovieDetailsMapper.map(movieDB)))
                } else emit(Resource.GenericDataError(response.errorCode, response.errorMessage))
            }
        }.flowOn(ioDispatcher)
    }

    private suspend fun updateDataBase(response: Resource.Success<APIMovieDetail?>) {
        response.data?.let {
            apiMovieDetailsToMovieDBMapper.map(
                it
            )
        }?.let { movieDao.upsertMovie(it) }
    }

}