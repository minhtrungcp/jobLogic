package com.example.joblogic.data

import com.example.joblogic.data.dto.CustomerDTO
import com.example.joblogic.data.dto.ProductDTO
import retrofit2.http.GET

interface Api {

    @GET("call")
    suspend fun getListCustomer(): List<CustomerDTO>

    @GET("buy")
    suspend fun getListProduct(): List<ProductDTO>
}