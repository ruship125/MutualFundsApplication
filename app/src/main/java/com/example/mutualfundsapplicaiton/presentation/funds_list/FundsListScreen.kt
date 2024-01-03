package com.example.mutualfundsapplicaiton.presentation.funds_list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mutualfundsapplicaiton.presentation.screen.Screen
import com.example.mutualfundsapplicaiton.presentation.funds_list.components.FundsListItem

@Composable
fun FundsListScreen(
    navController : NavController,
    viewModel : FundsListViewModel = hiltViewModel()
){
    val fundslist = viewModel.fundslist.value

    Box(modifier = Modifier.fillMaxSize()){
        if (fundslist.isLoading){
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
        if (fundslist.error.isNotBlank()){
            Text(
                text = fundslist.error.toString(),
                color = MaterialTheme.colors.onError,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(fundslist.fundsList.size) { i ->
                FundsListItem(
                    Funds = fundslist.fundsList[i],
                    onItemClick = {
                        navController.navigate(
                            Screen.FundsInfoScreen.route + "/${fundslist.fundsList[i].schemeCode}"
                        )
                    }
                )
            }
        }
    }
}