package com.example.joblogic.data.repository.sell_product

import com.example.joblogic.common.Resource
import com.example.joblogic.domain.model.Product
import com.example.joblogic.domain.repository.product.SellProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class SellProductRepositoryImpl @Inject constructor(
    private val dataBaseHelper: DataBaseHelper,
    private val mapperImplSell: SellProductModelMapperImpl
) : SellProductRepository {

    override fun getSellProducts(): Flow<Resource<List<Product>>> = flow {
        try {
            emit(Resource.Loading<List<Product>>())
            val response = dataBaseHelper.readData()
            emit(Resource.Success<List<Product>>(response.map { result -> mapperImplSell.from(result) }))
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

    override fun addSellProduct(product: Product) = dataBaseHelper.insertData(product)

    override fun isExistSellProducts(): Boolean = dataBaseHelper.isExistData()
}