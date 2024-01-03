package com.example.mutualfundsapplicaiton.presentation.funds_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.mutualfundsapplicaiton.domain.model.FundsList


@Composable
fun FundsListItem(
    Funds: FundsList,
    onItemClick: (FundsList) -> Unit
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(Funds) }
            .padding(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = Funds.schemeName,
            style = MaterialTheme.typography.h4,
            color = Color.Black,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = Funds.schemeCode.toString(),
            style = MaterialTheme.typography.body1,
            color = Color.Blue,
            textAlign = TextAlign.End,
            modifier = Modifier.align(Alignment.Bottom)
        )
    }
}