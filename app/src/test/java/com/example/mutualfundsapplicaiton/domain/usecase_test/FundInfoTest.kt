package com.example.mutualfundsapplicaiton.domain.usecase_test

import com.example.mutualfundsapplicaiton.data.repository.FakeRepo
import com.example.mutualfundsapplicaiton.domain.model.FundsInfo
import com.example.mutualfundsapplicaiton.domain.use_case.GetFundsInfoUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*

class FundInfoTest {

    private lateinit var getFundInfo: GetFundsInfoUseCase
    private lateinit var repo: FakeRepo

    @Before
    fun setUp(){
        repo = FakeRepo()
        getFundInfo = GetFundsInfoUseCase(repo)
    }

    @Test
    fun `get funds info`(){
        runBlocking {
            val res = getFundInfo.invoke(0).data
            assertTrue(res is FundsInfo)
            assertEquals(res?.meta?.scheme_code, 0)
        }
    }

}