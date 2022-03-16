package com.example.joblogic.domain.repository.customer

import com.example.joblogic.common.Resource
import com.example.joblogic.domain.model.Customer
import kotlinx.coroutines.flow.Flow

interface CustomerRepository {
    fun getCustomers(): Flow<Resource<List<Customer>>>
}