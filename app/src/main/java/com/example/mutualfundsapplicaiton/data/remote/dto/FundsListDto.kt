package com.example.mutualfundsapplicaiton.data.remote.dto

import com.example.mutualfundsapplicaiton.domain.model.FundsList

data class FundsListDto(
    val schemeCode: Int,
    val schemeName: String
)

fun FundsListDto.toFundsList(): FundsList {
    return FundsList(
        schemeCode = schemeCode,
        schemeName = schemeName
    )
}
