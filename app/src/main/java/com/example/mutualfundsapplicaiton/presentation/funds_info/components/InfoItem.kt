package com.example.mutualfundsapplicaiton.presentation.funds_info.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mutualfundsapplicaiton.domain.model.FundsInfo
import com.example.mutualfundsapplicaiton.common.Constants.SPACER_HEIGHT

@Composable
fun InfoItem(
    info: FundsInfo
){
    Column(
        modifier = Modifier.fillMaxHeight()
    ) {
        Text(
            text = "Fund Code: " + info.meta.scheme_code.toString(),
            style = MaterialTheme.typography.h5
        )
        Spacer(modifier = Modifier.height(SPACER_HEIGHT.dp))
        Text(
            text = "Name: " + info.meta.scheme_name,
            style = MaterialTheme.typography.h5
        )
        Spacer(modifier = Modifier.height(SPACER_HEIGHT.dp))
        Text(
            text = "Category: " + info.meta.scheme_category,
            style = MaterialTheme.typography.subtitle2
        )
        Spacer(modifier = Modifier.height(SPACER_HEIGHT.dp))
        Text(
            text = "Type: " + info.meta.scheme_type,
            style = MaterialTheme.typography.subtitle2
        )
        Spacer(modifier = Modifier.height(SPACER_HEIGHT.dp))
        Text(
            text = "Fund house: " + info.meta.fund_house,
            style = MaterialTheme.typography.subtitle2
        )
        Spacer(modifier = Modifier.height(SPACER_HEIGHT.dp))
    }
}