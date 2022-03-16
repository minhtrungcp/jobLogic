package com.example.joblogic.presenter.sell_product

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController

@Composable
fun SellProductScreen(
    navController: NavHostController,
    sellProductViewModel: SellProductViewModel = hiltViewModel()
) {
    val products = sellProductViewModel.products.value
    Scaffold(
        topBar = { DetailTopBar("Sell List", navController) }
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(products.size) { index ->
                    val product = products[index]
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                start = 15.dp,
                                top = 10.dp,
                                end = 15.dp,
                                bottom = 5.dp
                            )
                    ) {
                        Text(
                            text = "Name:"
                        )

                        Text(
                            text = product.name,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    horizontal = 5.dp
                                )
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                start = 15.dp,
                                top = 5.dp,
                                end = 15.dp,
                                bottom = 5.dp
                            )
                    ) {
                        Text(
                            text = "Price:"
                        )

                        Text(
                            text = product.price.toString(),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    horizontal = 5.dp
                                )
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                start = 15.dp,
                                top = 5.dp,
                                end = 15.dp,
                                bottom = 10.dp
                            )
                    ) {
                        Text(
                            text = "Quantity:"
                        )

                        Text(
                            text = product.quantity.toString(),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    horizontal = 5.dp
                                )
                        )
                    }
                    Divider(color = Color.Gray, thickness = 1.dp)
                }
            }
            if (sellProductViewModel.error.value.isNotBlank()) {
                Text(
                    text = sellProductViewModel.error.value,
                    color = MaterialTheme.colors.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .align(Alignment.Center)
                )
            } else
                if (sellProductViewModel.isLoading.value && products.isEmpty()) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
        }
    }
}

@Composable
fun DetailTopBar(title: String, navController: NavController) {
    TopAppBar(
        title = {
            Text(
                text = title,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        },
        navigationIcon = {
            IconButton(onClick = {
                navController.popBackStack()
            }) {
                Icon(
                    painter = painterResource(id = com.example.joblogic.R.drawable.ic_baseline_arrow_back_24),
                    contentDescription = null
                )
            }
        },
        actions = {
            Box(modifier = Modifier.size(width = 70.dp, height = 50.dp)) {}
        }
    )
}
