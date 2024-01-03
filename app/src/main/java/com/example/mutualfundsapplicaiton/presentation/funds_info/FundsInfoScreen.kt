package com.example.mutualfundsapplicaiton.presentation.funds_info

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mutualfundsapplicaiton.presentation.funds_info.components.FundGraph
import com.example.mutualfundsapplicaiton.presentation.funds_info.components.InfoItem

@Composable
fun FundsInfoScreen(
    viewModel : FundsInfoViewModel = hiltViewModel()
){
    val fundsInfo = viewModel.fundsInfo.value
    Box(modifier = Modifier.fillMaxSize()){
        fundsInfo.fundsInfo?.let { info ->
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(20.dp)
            ){
                item {
                    InfoItem(info = info)
                    FundGraph(info = info)
                }
            }
        }

        if (fundsInfo.error.isNotBlank()){
            Text(
                text = fundsInfo.error,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }
        if (fundsInfo.isLoading){
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}

