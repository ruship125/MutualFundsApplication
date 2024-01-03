package com.example.mutualfundsapplicaiton.presentation.screen

sealed class Screen(val route: String){
    object FundsListScreen: Screen("funds_list_screen")
    object FundsInfoScreen: Screen("funds_info_screen")
}
