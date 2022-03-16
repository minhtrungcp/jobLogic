package com.example.joblogic.domain.repository.product

import com.example.joblogic.common.Resource
import com.example.joblogic.data.local.db.ProductDAO
import com.example.joblogic.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface SellProductRepository {
    fun getSellProducts(): Flow<Resource<List<Product>>>
    fun addSellProduct(product: Product)
    fun isExistSellProducts(): Boolean
}