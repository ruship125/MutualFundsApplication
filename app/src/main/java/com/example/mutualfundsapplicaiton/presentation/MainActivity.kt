package com.example.mutualfundsapplicaiton.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mutualfundsapplicaiton.presentation.funds_list.FundsListScreen
import com.example.mutualfundsapplicaiton.presentation.funds_info.FundsInfoScreen
import com.example.mutualfundsapplicaiton.presentation.screen.Screen
import com.example.mutualfundsapplicaiton.presentation.theme.MutualFundsApplicaitonTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MutualFundsApplicaitonTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
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
                            route = Screen.FundsInfoScreen.route + "/{schemeCode}"
                        ){
                            FundsInfoScreen()
                        }
                    }
                }
            }
        }
    }
}

