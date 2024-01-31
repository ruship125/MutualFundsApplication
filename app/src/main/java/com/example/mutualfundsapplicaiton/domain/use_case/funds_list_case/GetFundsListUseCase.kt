package com.example.mutualfundsapplicaiton.domain.use_case.funds_list_case

import com.example.mutualfundsapplicaiton.common.Resource
import com.example.mutualfundsapplicaiton.domain.model.FundsList
import com.example.mutualfundsapplicaiton.domain.repository.MutualFundsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetFundsListUseCase @Inject constructor(
    private val mutualFundsRepository : MutualFundsRepository
) {

    operator fun invoke(): Flow<Resource<List<FundsList>>> = flow {
        emit(mutualFundsRepository.getFundsList())
    }
}