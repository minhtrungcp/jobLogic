package com.example.joblogic.data.repository.customer

import com.example.joblogic.data.dto.CustomerDTO
import com.example.joblogic.domain.model.Customer
import com.example.joblogic.domain.repository.DataMapper
import javax.inject.Inject

class CustomerModelMapperImpl @Inject constructor() : DataMapper<CustomerDTO,Customer> {
    override fun from(from: CustomerDTO): Customer {
        return Customer(from.id, from.name, from.number)
    }

    override fun to(from: Customer): CustomerDTO {
        return CustomerDTO(from.id, from.name, from.number)
    }
}