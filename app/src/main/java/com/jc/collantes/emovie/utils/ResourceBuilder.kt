package com.jc.collantes.emovie.utils

import com.jc.collantes.emovie.data.model.Resource
import com.jc.collantes.emovie.data.model.api.ApiResponse
import com.jc.collantes.emovie.data.model.api.GenericErrorApiResponse
import com.jc.collantes.emovie.data.model.api.SuccessApiResponse

class ResourceBuilder<T> {
    fun build(response : ApiResponse<T?>): Resource<T?> {
        return when (response) {
            is SuccessApiResponse<*> -> {
                val body = response.body as T
                Resource.Success(body)
            }
            else -> {
                Resource.GenericDataError(
                    (response as GenericErrorApiResponse).code,
                    response.message
                )
            }
        }
    }
}