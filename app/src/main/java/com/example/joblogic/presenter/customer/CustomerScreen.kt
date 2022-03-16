package com.example.joblogic.presenter.customer

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
import com.example.joblogic.R

@Composable
fun CustomerScreen(
    navController: NavHostController,
    customerViewModel: CustomerViewModel = hiltViewModel()
) {
    val customers = customerViewModel.customers.value
    Scaffold(
        topBar = { DetailTopBar("Call List", navController) }
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(customers.size) { index ->
                    val customer = customers[index]
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
                            text = customer.name,
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
                            text = "Number:"
                        )

                        Text(
                            text = customer.number,
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
            if (customerViewModel.error.value.isNotBlank()) {
                Text(
                    text = customerViewModel.error.value,
                    color = MaterialTheme.colors.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .align(Alignment.Center)
                )
            } else
                if (customerViewModel.isLoading.value && customers.isEmpty()) {
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
