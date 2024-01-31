package com.example.mutualfundsapplicaiton.presentation.funds_list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mutualfundsapplicaiton.presentation.funds_list.components.FundsListItem
import com.example.mutualfundsapplicaiton.common.Constants.SEARCH_FIELD_HEIGHT
import com.example.mutualfundsapplicaiton.common.Constants.SEARCH_FIELD_PADDING
import com.example.mutualfundsapplicaiton.common.Constants.SPACER_HEIGHT
import com.example.mutualfundsapplicaiton.common.Constants.ERROR_TEXT_PADDING
import com.example.mutualfundsapplicaiton.common.Constants.ERROR_TEXT_SIZE
import com.example.mutualfundsapplicaiton.common.Constants.ErrorMessage
import com.example.mutualfundsapplicaiton.common.Constants.SEARCH_FIELD_PLACEHOLDER
import com.example.mutualfundsapplicaiton.presentation.navigation.navigateToFundInfo


@Composable
fun FundsListScreen(
    navController : NavController,
    viewModel : FundsListViewModel = hiltViewModel()
) {
    val fundsList = viewModel.fundsList.value
    val searchQuery = viewModel.searchQuery.value

    Column(modifier = Modifier.fillMaxSize()) {
        OutlinedTextField(
            value = searchQuery,
            onValueChange = {
                viewModel.searchList(it)
            },
            placeholder = {
                Text(text = SEARCH_FIELD_PLACEHOLDER)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(SEARCH_FIELD_HEIGHT.dp)
                .padding(SEARCH_FIELD_PADDING.dp),
            maxLines = 1,
            singleLine = true
        )
        Spacer(modifier = Modifier.height(SPACER_HEIGHT.dp))
        Box(modifier = Modifier.fillMaxSize())  {
            if (fundsList.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Center))
            }
            fundsList.fundsList?.let {
                LazyColumn {
                    items(fundsList.fundsList.size) { i ->
                        FundsListItem(
                            Funds = fundsList.fundsList[i],
                            onItemClick = {
                                navigateToFundInfo(navController, it.schemeCode)
                            }
                        )
                    }
                }
            } ?: run {
                Text(
                    text = fundsList.error ?: ErrorMessage,
                    color = Color.Red,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = ERROR_TEXT_PADDING.dp)
                        .align(Center)
                        .size(ERROR_TEXT_SIZE.dp)
                )
            }
        }
    }
}

