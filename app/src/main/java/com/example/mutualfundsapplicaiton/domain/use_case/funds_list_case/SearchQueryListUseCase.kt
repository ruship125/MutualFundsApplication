package com.example.mutualfundsapplicaiton.domain.use_case.funds_list_case

import com.example.mutualfundsapplicaiton.common.Resource
import com.example.mutualfundsapplicaiton.domain.model.FundsList
import javax.inject.Inject
import com.example.mutualfundsapplicaiton.common.Constants.SEARCH_FUND_NOT_FOUND

class SearchQueryListUseCase @Inject constructor() {

    operator fun invoke(
        query: String,
        fundsList :List<FundsList>
    ): Resource<List<FundsList>> {
        return if (query.isNotBlank()) {
            val queriedList = fundsList.filter { i ->
                i.schemeName.contains(query, ignoreCase = true)
            }
            if (queriedList.isNotEmpty()) {
                Resource.Success(queriedList)
            } else {
                Resource.Error(SEARCH_FUND_NOT_FOUND)
            }
        } else {
            Resource.Success(fundsList)
        }
    }
}