package com.example.mutualfundsapplicaiton.domain.use_case

import com.example.mutualfundsapplicaiton.common.Resource
import com.example.mutualfundsapplicaiton.domain.model.FundsList
import com.example.mutualfundsapplicaiton.domain.repository.MutualFundsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchQueryListUseCase @Inject constructor(
    private val mutualFundsRepository : MutualFundsRepository,
) {

    operator fun invoke(query: String): Flow<Resource<List<FundsList>>> = flow {
        val res = mutualFundsRepository.getFundsList()
        if (res is Resource.Success) {
            var queriedlist = mutableListOf<FundsList>()
            res.data?.forEach { i ->
                if (i.schemeName.contains(query)) {
                    queriedlist.add(i)
                }
            }
            emit(Resource.Success(queriedlist.toList()))
        } else {
            emit(res)
        }
    }

}