package com.example.joblogic.data.repository.sell_product

import com.example.joblogic.data.local.db.ProductDAO
import com.example.joblogic.domain.model.Product
import com.example.joblogic.domain.repository.DataMapper
import javax.inject.Inject

class SellProductModelMapperImpl @Inject constructor(): DataMapper<ProductDAO,Product> {
    override fun from(from: ProductDAO): Product {
        return Product(from.id, from.name, from.price, from.quantity, from.type)
    }

    override fun to(from: Product): ProductDAO {
        return ProductDAO(from.id, from.name, from.price, from.quantity, from.type)
    }
}