package com.example.joblogic.data.repository.customer

import com.example.joblogic.common.Resource
import com.example.joblogic.data.Api
import com.example.joblogic.domain.model.Customer
import com.example.joblogic.domain.repository.customer.CustomerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CustomerRepositoryImpl @Inject constructor(
    private val api: Api,
    private val mapperImpl: CustomerModelMapperImpl
) : CustomerRepository {
    override fun getCustomers(): Flow<Resource<List<Customer>>> = flow {
        try {
            emit(Resource.Loading<List<Customer>>())
            val response = api.getListCustomer()
            emit(Resource.Success<List<Customer>>(response.map { result -> mapperImpl.from(result) }))
        } catch (e: HttpException) {
            emit(
                Resource.Error<List<Customer>>(
                    e.localizedMessage ?: "An unexpected error occurred"
                )
            )
        } catch (e: IOException) {
            emit(Resource.Error<List<Customer>>("Couldn't reach server, check your internet"))
        }
    }
}