package com.example.mutualfundsapplicaiton.domain.usecase_test.funds_info_test

import com.example.mutualfundsapplicaiton.common.Resource
import com.example.mutualfundsapplicaiton.data.remote.dto.Meta
import com.example.mutualfundsapplicaiton.domain.model.FundsInfo
import com.example.mutualfundsapplicaiton.domain.repository.MutualFundsRepository
import com.example.mutualfundsapplicaiton.domain.use_case.funds_info_case.GetFundsInfoUseCase
import com.example.mutualfundsapplicaiton.common.Constants.IOExceptionMessage
import com.example.mutualfundsapplicaiton.common.Constants.ErrorMessage
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.IOException
import kotlinx.coroutines.test.runBlockingTest


class GetFundsInfoUseCaseTest {


    private val repository = mockk<MutualFundsRepository>()
    private val useCase = GetFundsInfoUseCase(repository)

    @Test
    fun `invoke returns expected result`() = runBlockingTest {
        val schemeCode = 0
        val mockResponse = FundsInfo(
                                emptyList(),
                                Meta(
                                    "xyz",
                                    "xyz",
                                    0,
                                    "xyz",
                                    "xyz"
                                )
                            )
        coEvery { repository.getFundsInfo(schemeCode) } returns Resource.Success(mockResponse)

        val result = useCase(schemeCode)

        assertTrue(result is Resource.Success)
        assertEquals(Resource.Success(mockResponse).data, result.data)
    }

    @Test(expected = IOException::class)
    fun `return error message on IO Exception`() = runBlockingTest {
        coEvery { repository.getFundsInfo(0) } throws IOException()

        val result = useCase(0)

        assertTrue(result is Resource.Error)
        assertEquals(IOExceptionMessage, result.message)
    }

    @Test(expected = Exception::class)
    fun `return error message on Exception`() = runBlockingTest {
        coEvery { repository.getFundsInfo(0) } throws Exception()

        val result = useCase(0)

        assertTrue(result is Resource.Error)
        assertEquals(ErrorMessage, result.message)

    }

}
