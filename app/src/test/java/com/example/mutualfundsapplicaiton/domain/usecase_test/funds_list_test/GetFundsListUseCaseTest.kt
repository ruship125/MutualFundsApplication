package com.example.mutualfundsapplicaiton.domain.usecase_test.funds_list_test

import com.example.mutualfundsapplicaiton.common.Constants
import com.example.mutualfundsapplicaiton.common.Resource
import com.example.mutualfundsapplicaiton.domain.model.FundsList
import com.example.mutualfundsapplicaiton.domain.repository.MutualFundsRepository
import com.example.mutualfundsapplicaiton.domain.use_case.funds_list_case.GetFundsListUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.IOException

class GetFundsListUseCaseTest {

    private val repository = mockk<MutualFundsRepository>()
    private val useCase = GetFundsListUseCase(repository)

    @Test
    fun `invoke returns expected result`() = runBlockingTest {
        val mockResponse = Resource.Success(listOf<FundsList>())
        coEvery { repository.getFundsList() } returns mockResponse

        val result = useCase().first()

        // Assert
        assertEquals(mockResponse, result)
    }

    @Test(expected = IOException::class)
    fun `return error message on IO Exception`() = runBlockingTest {
        coEvery { repository.getFundsList() } throws IOException()

        val result = useCase().first()

        assertTrue(result is Resource.Error)
        assertEquals(Constants.IOExceptionMessage, result.message)
    }

    @Test(expected = Exception::class)
    fun `return error message on Exception`() = runBlockingTest {
        coEvery { repository.getFundsList() } throws Exception()

        val result = useCase().first()

        assertTrue(result is Resource.Error)
        assertEquals(Constants.ErrorMessage, result.message)
    }

}
