package com.example.joblogic.presenter.customer

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.joblogic.common.Resource
import com.example.joblogic.domain.model.Customer
import com.example.joblogic.domain.use_case.customer.CustomerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CustomerViewModel @Inject constructor(
    private val customerUseCase: CustomerUseCase
) : ViewModel() {

    private val _customers = mutableStateOf<List<Customer>>(emptyList())
    val customers: State<List<Customer>> = _customers
    private val _error = mutableStateOf("")
    val error: State<String> = _error
    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    init {
        getCustomerList()
    }
    private fun appendCustomers(customers: List<Customer>) {
        val current = ArrayList(_customers.value)
        current.addAll(customers)
        _customers.value = current
    }

    fun getCustomerList() {
        customerUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    appendCustomers(result.data ?: emptyList())
                    _isLoading.value = false
                    _error.value = ""
                }
                is Resource.Error -> {
                    _error.value = result.message ?: "An unexpected error occurred"

                }
                is Resource.Loading -> {
                    _isLoading.value = true
                }
            }
        }.launchIn(viewModelScope)
    }
}
