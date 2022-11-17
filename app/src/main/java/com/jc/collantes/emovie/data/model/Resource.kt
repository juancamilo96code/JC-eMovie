package com.jc.collantes.emovie.data.model

import com.jc.collantes.emovie.data.model.api.ApiResponse
import com.jc.collantes.emovie.data.model.api.GenericErrorApiResponse
import com.jc.collantes.emovie.data.model.api.SuccessApiResponse

sealed class Resource <T>(
    var data: T? = null,
    val errorCode: Int? = null,
    val errorMessage: String? = null,
    val correlationId: String? = null,
    val tryAgainOn: Boolean? = null
) {
    class Sleep<T>(data: T? = null) : Resource<T>(data)
    class Success<T>(data: T? = null) : Resource<T>(data)
    class Loading<T>(data: T? = null) : Resource<T>(data)
    class GenericDataError<T>(errorCode: Int? = null, errorMessage: String? = null) :
        Resource<T>(null, errorCode, errorMessage)

    override fun toString(): String {
        return when (this) {
            is Sleep-> "Sleep"
            is Success<*> -> "Success[data=$data]"
            is Loading<T> -> "Loading"
            is GenericDataError -> "Error[exception=$errorCode]"
        }
    }
}
