package com.example.mutualfundsapplicaiton.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mutualfundsapplicaiton.common.Constants
import com.example.mutualfundsapplicaiton.presentation.funds_info.FundsInfoScreen
import com.example.mutualfundsapplicaiton.presentation.funds_list.FundsListScreen

@Composable
fun NavGraph(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.FundsListScreen.route
    ){
        composable(
            route = Screen.FundsListScreen.route
        ){
            FundsListScreen(navController = navController)
        }
        composable(
            route = Screen.FundsInfoScreen.route + "/{${Constants.PARAM_SCHEME_CODE}}"
        ){
            FundsInfoScreen()
        }
    }
}

fun navigateToFundInfo(navController: NavController, schemeCode: Int) {
    val route = Screen.FundsInfoScreen.route + "/$schemeCode"
    navController.navigate(route)
}
