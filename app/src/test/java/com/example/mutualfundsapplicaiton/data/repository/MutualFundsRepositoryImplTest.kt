package com.example.mutualfundsapplicaiton.data.repository

import com.example.mutualfundsapplicaiton.common.Constants.ErrorMessage
import com.example.mutualfundsapplicaiton.common.Constants.IOExceptionMessage
import com.example.mutualfundsapplicaiton.common.Resource
import com.example.mutualfundsapplicaiton.data.remote.MFapi
import com.example.mutualfundsapplicaiton.data.remote.dto.*
import com.example.mutualfundsapplicaiton.domain.model.FundsInfo
import com.example.mutualfundsapplicaiton.domain.model.FundsList
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.IOException

class MutualFundsRepositoryImplTest {

    private val api = mockk<MFapi>()
    private val repository = MutualFundsRepositoryImpl(api)

    @Test
    fun `getFundsList returns expected result`() = runBlocking {
        val mockResponse = listOf<FundsList>()
        coEvery { api.getFundsList().map { it.toFundsList() } } returns mockResponse

        val result = repository.getFundsList()

        assertTrue(result is Resource.Success)
        assertTrue(result.data is List<FundsList>)
        assertEquals(mockResponse.size, result.data?.size ?: 0)
        assertEquals(Resource.Success(mockResponse).data, result.data)
    }


    @Test
    fun `getFundsInfo returns expected result`() = runBlocking {
        val schemeCode = 100027
        val mockResponse = FundsInfoDto(emptyList(),
                            Meta(
                                "xyz",
                                "xyz",
                                10027,
                                "xyz",
                                "xyz"
                            ), "Active")
        coEvery { api.getFundsInfo(schemeCode) } returns mockResponse

        val result = repository.getFundsInfo(schemeCode)

        assertTrue(result is Resource.Success)
        assertTrue(result.data is FundsInfo)
        assertEquals(Resource.Success(mockResponse.toFundsInfo()).data, result.data)
    }

    @Test
    fun `getFundsList returns error on network failure`() = runBlocking {

        coEvery { api.getFundsList() } throws IOException()

        val result = repository.getFundsList()

        assertTrue(result is Resource.Error)
        assertEquals(IOExceptionMessage, result.message)
    }

    @Test
    fun `getFundsInfo returns error on network failure`() = runBlockingTest {

        coEvery { api.getFundsInfo(0) } throws IOException()

        val result = repository.getFundsInfo(0)

        assertTrue(result is Resource.Error)
        assertEquals(IOExceptionMessage, result.message)
    }

    @Test
    fun `getFundsList returns error on Exception`() = runBlockingTest {

        coEvery { api.getFundsList() } throws Exception()

        val result = repository.getFundsList()

        assertTrue(result is Resource.Error)
        assertEquals(ErrorMessage, result.message)
    }

    @Test
    fun `getFundsInfo returns error on Exception`() = runBlocking {

        coEvery { api.getFundsInfo(0) } throws Exception()

        val result = repository.getFundsInfo(0)

        assertTrue(result is Resource.Error)
        assertEquals(ErrorMessage, result.message)
    }

}
