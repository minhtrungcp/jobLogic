package com.example.joblogic.domain.use_case.product

import com.example.joblogic.common.Resource
import com.example.joblogic.domain.model.Product
import com.example.joblogic.domain.repository.product.SellProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SellProductUseCase @Inject constructor(
    private val sellProductRepository: SellProductRepository
) {
    operator fun invoke(): Flow<Resource<List<Product>>> =
        sellProductRepository.getSellProducts()

    fun addProduct(product: Product) = sellProductRepository.addSellProduct(product)

    fun isExistSellProducts() = sellProductRepository.isExistSellProducts()
}