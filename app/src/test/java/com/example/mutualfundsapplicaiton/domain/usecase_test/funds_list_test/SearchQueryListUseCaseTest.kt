package com.example.mutualfundsapplicaiton.domain.usecase_test.funds_list_test

import com.example.mutualfundsapplicaiton.common.Resource
import com.example.mutualfundsapplicaiton.domain.model.FundsList
import com.example.mutualfundsapplicaiton.common.Constants.SEARCH_FUND_NOT_FOUND
import com.example.mutualfundsapplicaiton.domain.use_case.funds_list_case.SearchQueryListUseCase
import org.junit.Assert.assertEquals
import org.junit.Test

class SearchQueryListUseCaseTest {

    private val useCase = SearchQueryListUseCase()

    @Test
    fun `invoke returns all funds when query is blank`() {
        val fundsList = listOf(FundsList(1,"Scheme 1"), FundsList(2,"Scheme 2"))

        val result = useCase("", fundsList)

        assertEquals(fundsList, result.data)
    }

    @Test
    fun `invoke returns matching funds when query is not blank`() {
        val fundsList = listOf(FundsList(1,"Scheme 1"), FundsList(2,"Scheme 2"))
        val query = "1"

        val result = useCase(query, fundsList)

        assertEquals(listOf(FundsList(1,"Scheme 1")), result.data)
    }

    @Test
    fun `invoke returns error when query is not blank and no funds match`() {
        val fundsList = listOf(FundsList(1,"Scheme 1"), FundsList(2,"Scheme 2"))
        val query = "3"

        val result = useCase(query, fundsList)

        assertEquals(Resource.Error<List<FundsList>>(SEARCH_FUND_NOT_FOUND).message, result.message)
    }
}
