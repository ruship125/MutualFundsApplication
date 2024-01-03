package com.example.mutualfundsapplicaiton.presentation.funds_info.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mutualfundsapplicaiton.domain.model.FundsInfo

@Composable
fun InfoItem(
    info: FundsInfo
){
    Column(
        modifier = Modifier.fillMaxHeight()
    ) {
        Text(
            text = info.meta.scheme_code.toString()
        )
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = info.meta.scheme_category
        )
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = info.meta.scheme_name
        )
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = info.meta.scheme_type
        )
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = info.meta.fund_house
        )
        Spacer(modifier = Modifier.height(25.dp))
    }
}