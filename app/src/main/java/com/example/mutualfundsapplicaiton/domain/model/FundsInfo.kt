package com.example.mutualfundsapplicaiton.domain.model

import com.example.mutualfundsapplicaiton.data.remote.Dto.Data
import com.example.mutualfundsapplicaiton.data.remote.Dto.Meta

data class FundsInfo(
    val `data`: List<Data>,
    val meta: Meta
)
