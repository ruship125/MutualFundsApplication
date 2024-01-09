package com.example.mutualfundsapplicaiton.domain.usecase_test

import com.example.mutualfundsapplicaiton.data.repository.FakeRepo
import com.example.mutualfundsapplicaiton.domain.model.FundsList
import com.example.mutualfundsapplicaiton.domain.use_case.SearchQueryListUseCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*

class SearchQueryListTest {

    private lateinit var searchQueryListUseCase: SearchQueryListUseCase
    private lateinit var repo: FakeRepo

    @Before
    fun setUp(){
        repo = FakeRepo()
        searchQueryListUseCase = SearchQueryListUseCase(repo)
    }

    @Test
    fun `get list`(){
        val query = "a"
        runBlocking {
            val res = searchQueryListUseCase.invoke(query).first().data
            assertTrue(res is List<FundsList>)
            val firtres = res?.get(0)
            assertNotNull(firtres?.schemeName?.contains("HDFC"))
        }
    }
}