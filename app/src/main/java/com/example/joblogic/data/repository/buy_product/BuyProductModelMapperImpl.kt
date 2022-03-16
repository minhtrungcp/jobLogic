package com.example.joblogic.data.repository.buy_product

import com.example.joblogic.data.dto.ProductDTO
import com.example.joblogic.domain.model.Product
import com.example.joblogic.domain.repository.DataMapper
import javax.inject.Inject

class BuyProductModelMapperImpl @Inject constructor(): DataMapper<ProductDTO,Product> {
    override fun from(from: ProductDTO): Product {
        return Product(from.id, from.name, from.price, from.quantity, from.type)
    }

    override fun to(from: Product): ProductDTO {
        return ProductDTO(from.id, from.name, from.price, from.quantity, from.type)
    }
}