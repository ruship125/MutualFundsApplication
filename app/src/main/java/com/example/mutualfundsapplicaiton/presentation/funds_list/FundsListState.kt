package com.example.mutualfundsapplicaiton.presentation.funds_list

import com.example.mutualfundsapplicaiton.domain.model.FundsList

data class FundsListState(
    val isLoading: Boolean = false,
    val fundsList : List<FundsList> = emptyList(),
    val error : String = ""
)