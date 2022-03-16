package com.example.joblogic.domain.repository.product

import com.example.joblogic.common.Resource
import com.example.joblogic.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface BuyProductRepository {
    fun getBuyProducts(): Flow<Resource<List<Product>>>
}