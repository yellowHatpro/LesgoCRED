package org.yellowhatpro.lesgocred.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.yellowhatpro.lesgocred.data.CategoryData
import org.yellowhatpro.lesgocred.data.Item
import org.yellowhatpro.lesgocred.data.repo.ApiRepository
import org.yellowhatpro.lesgocred.utils.Response

class MainViewModel : ViewModel() {
    private val repository = ApiRepository()

    private val _apiResponse = MutableStateFlow<Response<CategoryData>>(Response.loading())
    val apiResponse: StateFlow<Response<CategoryData>> = _apiResponse

    private val _selectedCategory = MutableStateFlow<Response<Item>>(Response.loading())
    val selectedCategory: StateFlow<Response<Item>> = _selectedCategory

    suspend fun fetchApiData() {
        try {
            val response = repository.getApiData()

            if (response.isSuccessful) {
                val responseBody = response.body()
                responseBody?.let { res ->
                    _apiResponse.value = Response.success(res)
                }
            } else {
                _apiResponse.value = Response.failure()
            }
        } catch (e: Exception) {
            _apiResponse.value = Response.failure()
        }
    }

    fun setCategory(category: String = "mint") {

        val response = _apiResponse.value
        when (response.status) {
            Response.Status.SUCCESS -> {
                val categoryData = response.data?.data?.firstNotNullOf { data ->
                    data.section_properties.items.find { item ->
                        item.id == category
                    }
                }
                categoryData?.let { categoryItem ->
                    _selectedCategory.value = Response.success(categoryItem)
                }
            }

            Response.Status.FAILED -> {
                _selectedCategory.value = Response.failure()
            }

            Response.Status.LOADING -> {
                _selectedCategory.value = Response.loading()

            }
        }
    }
}