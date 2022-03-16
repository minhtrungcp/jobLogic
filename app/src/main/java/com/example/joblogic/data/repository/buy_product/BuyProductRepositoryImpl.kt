package com.example.joblogic.data.repository.buy_product

import com.example.joblogic.common.Resource
import com.example.joblogic.data.Api
import com.example.joblogic.domain.model.Customer
import com.example.joblogic.domain.model.Product
import com.example.joblogic.domain.repository.product.BuyProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class BuyProductRepositoryImpl @Inject constructor(
    private val api: Api,
    private val mapperImplBuy: BuyProductModelMapperImpl
) : BuyProductRepository {

    override fun getBuyProducts(): Flow<Resource<List<Product>>> = flow {
        try {
            emit(Resource.Loading<List<Product>>())
            val response = api.getListProduct()
            emit(Resource.Success<List<Product>>(response.map { result -> mapperImplBuy.from(result) }))
        } catch (e: HttpException) {
            emit(
                Resource.Error<List<Product>>(
                    e.localizedMessage ?: "An unexpected error occurred"
                )
            )
        } catch (e: IOException) {
            emit(Resource.Error<List<Product>>("Couldn't reach server, check your internet"))
        }
    }
}