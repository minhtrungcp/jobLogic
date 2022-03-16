package com.example.joblogic.presenter.main_screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.joblogic.presenter.Screen


@Composable
fun MainScreen(
    navController: NavHostController,
    mainViewModel: MainViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = { TopBar() }) {
        val padding = 15.dp
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                DefineButton(displayText = "Call List", onClick = {
                    navController.navigate(Screen.CustomerScreen.route)
                })
                Spacer(Modifier.size(padding))
                DefineButton(displayText = "Buy List", onClick = {
                    navController.navigate(Screen.BuyProductScreen.route)
                })
                Spacer(Modifier.size(padding))
                DefineButton(displayText = "Sell List", onClick = {
                    navController.navigate(Screen.SellProductScreen.route)
                })
            }
    }
}

@Composable
fun TopBar() {
    TopAppBar(
        title = {
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Main Screen",
                    modifier = Modifier.align(Alignment.Center),
                )
            }
        }
    )
}

@Composable
fun DefineButton(
    displayText: String,
    onClick: (() -> Unit)
) {
   Button(onClick = onClick) {
       Text(text = displayText)
   }
}



