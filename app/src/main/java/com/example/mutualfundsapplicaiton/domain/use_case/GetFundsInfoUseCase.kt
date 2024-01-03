package com.example.mutualfundsapplicaiton.domain.use_case

import com.example.mutualfundsapplicaiton.common.Resource
import com.example.mutualfundsapplicaiton.domain.model.FundsInfo
import com.example.mutualfundsapplicaiton.domain.repository.MutualFundsRepository
import javax.inject.Inject

class GetFundsInfoUseCase @Inject constructor(
    private val mutualFundsRepository : MutualFundsRepository
) {

    suspend operator fun invoke(schemeCode: Int): Resource<FundsInfo> {
        return mutualFundsRepository.getFundsInfo(schemeCode)
    }
}