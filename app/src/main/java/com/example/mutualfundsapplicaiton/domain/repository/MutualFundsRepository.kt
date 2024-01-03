package com.example.mutualfundsapplicaiton.domain.repository

import com.example.mutualfundsapplicaiton.common.Resource
import com.example.mutualfundsapplicaiton.domain.model.FundsInfo
import com.example.mutualfundsapplicaiton.domain.model.FundsList

interface MutualFundsRepository {

    suspend fun getFundsList(): Resource<List<FundsList>>

    suspend fun getFundsInfo(schemeCode: Int): Resource<FundsInfo>
}