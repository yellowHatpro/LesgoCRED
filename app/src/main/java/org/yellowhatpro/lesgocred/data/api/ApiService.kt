package org.yellowhatpro.lesgocred.data.api

import org.yellowhatpro.lesgocred.data.CategoryData
import retrofit2.Response
import retrofit2.http.GET


interface ApiService {
    @GET("lesgocred")
    suspend fun getApiData() : Response<CategoryData>

}
