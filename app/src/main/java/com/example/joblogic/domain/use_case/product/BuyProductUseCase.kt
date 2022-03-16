package com.example.joblogic.domain.use_case.product

import com.example.joblogic.common.Resource
import com.example.joblogic.domain.model.Product
import com.example.joblogic.domain.repository.product.BuyProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BuyProductUseCase @Inject constructor(
    private val repositoryBuy: BuyProductRepository
) {
    operator fun invoke(): Flow<Resource<List<Product>>> =
        repositoryBuy.getBuyProducts()
}