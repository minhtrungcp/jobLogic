package com.example.joblogic.presenter

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.joblogic.presenter.buy_product.BuyProductScreen
import com.example.joblogic.presenter.customer.CustomerScreen
import com.example.joblogic.presenter.main_screen.MainScreen
import com.example.joblogic.presenter.sell_product.SellProductScreen


@Composable
fun NavHostController(
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.MainScreen.route
    ) {
        composable(
            route = Screen.MainScreen.route
        ) {
            MainScreen(navController)
        }
        composable(
            route = Screen.CustomerScreen.route
        ) {
            CustomerScreen(navController)
        }
        composable(
            route = Screen.BuyProductScreen.route
        ) {
            BuyProductScreen(navController)
        }
        composable(
            route = Screen.SellProductScreen.route
        ) {
            SellProductScreen(navController)
        }
    }
}