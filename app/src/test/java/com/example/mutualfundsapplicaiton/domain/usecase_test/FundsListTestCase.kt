package com.example.mutualfundsapplicaiton.domain.usecase_test

import com.example.mutualfundsapplicaiton.common.Resource
import com.example.mutualfundsapplicaiton.data.repository.FakeRepo
import com.example.mutualfundsapplicaiton.domain.model.FundsList
import com.example.mutualfundsapplicaiton.domain.use_case.GetFundsListUseCase
import org.junit.Assert.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class FundsListTestCase {

    private lateinit var getFundsList: GetFundsListUseCase
    private lateinit var repo: FakeRepo

    @Before
    fun setUp(){
        repo = FakeRepo()
        getFundsList = GetFundsListUseCase(repo)
    }

    @Test
    fun `get list of funds`(){
        runBlocking {
            val list = getFundsList.invoke().first().data
            assertTrue(list is List<FundsList>)
        }
    }

}