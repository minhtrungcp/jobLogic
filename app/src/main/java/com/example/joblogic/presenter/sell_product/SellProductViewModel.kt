package com.example.joblogic.presenter.sell_product

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.joblogic.common.Resource
import com.example.joblogic.domain.model.Product
import com.example.joblogic.domain.use_case.product.BuyProductUseCase
import com.example.joblogic.domain.use_case.product.SellProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SellProductViewModel @Inject constructor(
    private val sellProductUseCase: SellProductUseCase
) : ViewModel() {

    private val _products = mutableStateOf<List<Product>>(emptyList())
    val products: State<List<Product>> = _products
    private val _error = mutableStateOf("")
    val error: State<String> = _error
    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val sellProductsTemp: List<Product> = listOf(
        Product(1, "Table", 12000, 1, 2),
        Product(2, "TV", 38000, 2, 2),
        Product(3, "iPhone X", 150000, 1, 2)
    )

    init {
        if (sellProductUseCase.isExistSellProducts()) {
            getSellProductList()
        } else insertProduct()
    }

    private fun appendProducts(products: List<Product>) {
        val current = ArrayList(_products.value)
        current.addAll(products)
        _products.value = current
    }

    private fun insertProduct() {
        sellProductsTemp.onEach { product ->
            sellProductUseCase.addProduct(product)
        }
        getSellProductList()
    }

    private fun getSellProductList() {
        sellProductUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    appendProducts(result.data ?: emptyList())
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
