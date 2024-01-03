package com.example.mutualfundsapplicaiton.presentation.funds_info.components

import android.graphics.Point
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mutualfundsapplicaiton.domain.model.FundsInfo


@Composable
fun FundGraph(
    info: FundsInfo
){
    val data = info.data
    Box(modifier = Modifier.fillMaxWidth()){
        Column {
            data.forEach { i ->
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(text = i.date)
                    Text(text = i.nav)
                }
            }
        }
    }
}