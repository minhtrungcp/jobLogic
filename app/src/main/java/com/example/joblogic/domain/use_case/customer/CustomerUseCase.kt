package com.example.joblogic.domain.use_case.customer

import com.example.joblogic.common.Resource
import com.example.joblogic.domain.model.Customer
import com.example.joblogic.domain.repository.customer.CustomerRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CustomerUseCase @Inject constructor(
    private val repository: CustomerRepository
) {
    operator fun invoke(): Flow<Resource<List<Customer>>> =
        repository.getCustomers()
}