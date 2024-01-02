package org.yellowhatpro.lesgocred.data.repo

import org.yellowhatpro.lesgocred.data.CategoryData
import org.yellowhatpro.lesgocred.data.api.RetrofitInstance
import retrofit2.Response

class ApiRepository {
    private val apiService = RetrofitInstance.apiService

    suspend fun getApiData(): Response<CategoryData> {
        return apiService.getApiData()
    }
}