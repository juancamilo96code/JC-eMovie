package com.jc.collantes.emovie.domain.usescases

import com.google.gson.JsonObject
import com.jc.collantes.emovie.data.model.Resource
import com.jc.collantes.emovie.data.model.ui.SimpleMovieItem

interface GetMoviesUseCase {

    suspend operator fun invoke(filterYear: String? = null, filterLanguage: String? = null): Resource<List<SimpleMovieItem>>

}