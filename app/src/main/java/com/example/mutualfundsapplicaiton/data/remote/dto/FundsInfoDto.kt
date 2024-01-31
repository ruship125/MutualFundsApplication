package com.example.mutualfundsapplicaiton.data.remote.dto

import com.example.mutualfundsapplicaiton.domain.model.FundsInfo

data class FundsInfoDto(
    val data: List<Data>,
    val meta: Meta,
    val status: String
)

fun FundsInfoDto.toFundsInfo(): FundsInfo {
    return FundsInfo(
        data = data,
        meta = meta,
    )
}