package com.jc.collantes.emovie.data.repositories

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jc.collantes.emovie.data.datasource.MovieVideosDataSource
import com.jc.collantes.emovie.data.mappers.APIMovieVideosToMovieVideosMapper
import com.jc.collantes.emovie.data.model.Resource
import com.jc.collantes.emovie.data.model.api.APIMovieVideo
import com.jc.collantes.emovie.data.model.api.APIMovieVideosResponse
import com.jc.collantes.emovie.data.model.ui.MovieVideo
import com.jc.collantes.emovie.data.service.local.MovieDao
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.reflect.Type
import kotlin.coroutines.CoroutineContext

class MovieVideoRepositoryImpl(
    private val movieVideosDataSource: MovieVideosDataSource,
    private val apiMovieVideosToMovieVideosMapper: APIMovieVideosToMovieVideosMapper,
    private val movieDao: MovieDao,
    private val ioDispatcher: CoroutineContext
) : MovieVideoRepository {

    val gson = Gson()

    override suspend fun getMovieVideos(movieId: Long): Flow<Resource<List<MovieVideo?>>> {
        return flow {
            val response = movieVideosDataSource.getMovieVideos(movieId)

            if (response is Resource.Success){
                coroutineScope {
                    async(ioDispatcher) {
                        updateDataBase(movieId,response)
                    }
                }
                emit(Resource.Success(response.data?.results?.map {
                    apiMovieVideosToMovieVideosMapper.map(it)
                }))
            }else{
                val movie = movieDao.getMovieDetails(movieId)
                if (movie?.videosData != null){
                    val listVideosClassObjects: Type = object : TypeToken<List<APIMovieVideo>>(){}.type
                    val listVideosAPI = gson.fromJson<List<APIMovieVideo>>(movie.videosData,listVideosClassObjects)
                    val listVideos = listVideosAPI.map {
                        apiMovieVideosToMovieVideosMapper.map(it)
                    }
                    emit(Resource.Success(listVideos))
                }else{
                    emit(Resource.GenericDataError(response.errorCode,response.errorMessage))
                }
            }
        }.flowOn(ioDispatcher)
    }

    private suspend fun updateDataBase(movieId: Long, response: Resource.Success<APIMovieVideosResponse?>) {
        val movie = movieDao.getMovieDetails(movieId)
        movie?.videosData = gson.toJson(response.data?.results)
        movie?.let { movieDao.upsertMovie(it) }
    }
}