package com.example.mutualfundsapplicaiton.presentation.funds_info

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mutualfundsapplicaiton.presentation.funds_info.components.Graph
import com.example.mutualfundsapplicaiton.presentation.funds_info.components.InfoItem
import com.example.mutualfundsapplicaiton.common.Constants.ERROR_TEXT_PADDING
import com.example.mutualfundsapplicaiton.common.Constants.ERROR_TEXT_SIZE
import com.example.mutualfundsapplicaiton.common.Constants.SPACER_HEIGHT
import com.example.mutualfundsapplicaiton.common.Constants.FUND_INFO_CONTENT_PADDING
import com.example.mutualfundsapplicaiton.common.Constants.ErrorMessage

@Composable
fun FundsInfoScreen(
    viewModel : FundsInfoViewModel = hiltViewModel()
){
    val fundsInfo = viewModel.fundsInfo.value
    Box(modifier = Modifier.fillMaxSize()){
        if (fundsInfo.isLoading){
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
        fundsInfo.fundsInfo?.let { info ->
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(FUND_INFO_CONTENT_PADDING.dp)
            ) {
                item {
                    InfoItem(info = info)
                    Spacer(modifier = Modifier.height(SPACER_HEIGHT.dp))
                    Graph(info = info)
                }
            }
        } ?: run {
            Text(
                text = fundsInfo.error ?: ErrorMessage,
                color = Color.Red,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = ERROR_TEXT_PADDING.dp)
                    .align(Alignment.Center)
                    .size(ERROR_TEXT_SIZE.dp)
            )
        }
    }
}

