package com.example.mutualfundsapplicaiton.presentation.funds_info

import com.example.mutualfundsapplicaiton.domain.model.FundsInfo

data class FundsInfoState(
    val isLoading : Boolean = true,
    val fundsInfo : FundsInfo? = null,
    val error : String? = "",
)


