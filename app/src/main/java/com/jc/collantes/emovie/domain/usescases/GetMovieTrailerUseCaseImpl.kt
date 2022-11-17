package com.jc.collantes.emovie.domain.usescases

import com.jc.collantes.emovie.data.model.Resource
import com.jc.collantes.emovie.data.model.ui.MovieVideo
import com.jc.collantes.emovie.data.repositories.MovieVideoRepository
import com.jc.collantes.emovie.utils.TRAILER_TYPE
import com.jc.collantes.emovie.utils.YOUTUBE_SITE


class GetMovieTrailerUseCaseImpl(
    private val movieVideoRepository: MovieVideoRepository
) : GetMovieTrailerUseCase {

    private lateinit var movieVideoResource: Resource<MovieVideo?>

    override suspend fun invoke(movieId: Long): Resource<MovieVideo?> {

        movieVideoRepository.getMovieVideos(movieId).collect { _videosList ->
            if (_videosList is Resource.Success) {
                val validList = _videosList.data?.filter {
                    it?.site.equals(YOUTUBE_SITE) && it?.type.equals(TRAILER_TYPE)
                }
                if (validList != null) {
                    movieVideoResource = if (validList.isNotEmpty()) {
                        Resource.Success(validList[0])
                    } else {
                        Resource.Success(null)
                    }
                } else Resource.Success(null)
            } else
                movieVideoResource =
                    Resource.GenericDataError(_videosList.errorCode, _videosList.errorMessage)
        }
        return movieVideoResource

    }

}