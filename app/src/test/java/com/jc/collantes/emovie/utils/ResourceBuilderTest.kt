package com.jc.collantes.emovie.utils

import com.jc.collantes.emovie.data.model.Resource
import com.jc.collantes.emovie.data.model.api.APIMovieDetail
import com.jc.collantes.emovie.data.model.api.GenericErrorApiResponse
import com.jc.collantes.emovie.data.model.api.SuccessApiResponse
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Test

class ResourceBuilderTest{

    @Test
    fun buildResourceFromSuccessApiResponse(){
        val response = SuccessApiResponse(APIMovieDetail(0))
        val resource = ResourceBuilder<APIMovieDetail>().build(response)
        MatcherAssert.assertThat(resource.data,CoreMatchers.instanceOf(APIMovieDetail::class.java))
    }

    @Test
    fun buildResourceFromFailedApiResponse(){
        val response = GenericErrorApiResponse()
        val resource = ResourceBuilder<APIMovieDetail>().build(response)
        MatcherAssert.assertThat(resource,CoreMatchers.instanceOf(Resource.GenericDataError::class.java))
    }

}