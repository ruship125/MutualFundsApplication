package com.example.mutualfundsapplicaiton.domain.model

import com.example.mutualfundsapplicaiton.data.remote.dto.Data
import com.example.mutualfundsapplicaiton.data.remote.dto.Meta

data class FundsInfo(
    val `data`: List<Data>,
    val meta: Meta
)
