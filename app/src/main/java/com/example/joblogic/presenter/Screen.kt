package com.example.joblogic.presenter

sealed class Screen(val route: String){
    object MainScreen: Screen("main_screen")
    object CustomerScreen: Screen("customer_screen")
    object BuyProductScreen: Screen("buy_product_screen")
    object SellProductScreen: Screen("sell_product_screen")
}