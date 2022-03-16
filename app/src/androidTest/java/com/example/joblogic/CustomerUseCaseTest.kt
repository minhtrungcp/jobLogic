package com.example.joblogic

import com.example.joblogic.domain.model.Customer
import kotlinx.coroutines.runBlocking

class CustomerUseCaseTest {

    @Mock
    repository: CustomerRepository

    private User
    private val customer: List<Customer> = listOf(
        Customer(1,"abc","1234567"),
        Customer(2,"def","1234567")
    )

    get_Customer_List_Success() {
        runBlocking {
            rep

        }

    }

}