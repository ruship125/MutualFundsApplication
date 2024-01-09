package com.example.mutualfundsapplicaiton.presentation.funds_list

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
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
    val searchquery = viewModel.searchQuery

    Box(modifier = Modifier.fillMaxSize()){
        if (fundslist.isLoading){
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else if (fundslist.error.isNotBlank()){
            Text(
                text = fundslist.error,
                color = MaterialTheme.colors.onError,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
                    .size(50.dp)
            )
        } else {
            Column( modifier = Modifier.fillMaxSize() ) {
                OutlinedTextField(
                    value = searchquery.value,
                    onValueChange = {
                        searchquery.value = it
                        viewModel.searchQuerylist()
                    },
                    placeholder = {
                        Text(text = "Search...")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .padding(16.dp),
                    maxLines = 1,
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(10.dp))
                LazyColumn(modifier = Modifier.fillMaxWidth()) {
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
    }
}