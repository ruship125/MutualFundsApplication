package com.example.mutualfundsapplicaiton.presentation.funds_Info

import androidx.lifecycle.SavedStateHandle
import com.example.mutualfundsapplicaiton.common.Constants.IOExceptionMessage
import com.example.mutualfundsapplicaiton.common.Constants.PARAM_SCHEME_CODE
import com.example.mutualfundsapplicaiton.common.Constants.ErrorMessage
import com.example.mutualfundsapplicaiton.common.Resource
import com.example.mutualfundsapplicaiton.data.remote.dto.Meta
import com.example.mutualfundsapplicaiton.domain.model.FundsInfo
import com.example.mutualfundsapplicaiton.domain.use_case.funds_info_case.GetFundsInfoUseCase
import com.example.mutualfundsapplicaiton.presentation.funds_info.FundsInfoViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertEquals

@ExperimentalCoroutinesApi
class FundsInfoViewModelTest {

    private val testDispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    private val getFundsInfoUseCase = mockk<GetFundsInfoUseCase>()
    private val savedStateHandle: SavedStateHandle = mockk()

    @Test
    fun `getFundsInfo update fundsInfo state`() = runBlockingTest{
        val schemeCode = 1
        val fundsInfo = FundsInfo(
                            emptyList(),
                            Meta(
                                "xyz",
                                "xyz",
                                1,
                                "xyz",
                                "xyz"
                            )
                        )
        val response = Resource.Success(fundsInfo)
        coEvery { savedStateHandle.get<String>(PARAM_SCHEME_CODE) } returns "1"
        coEvery { getFundsInfoUseCase(schemeCode) } returns response

        val viewModel = FundsInfoViewModel(getFundsInfoUseCase,savedStateHandle)

        val state = viewModel.fundsInfo.value
        assertEquals(false, state.isLoading)
        assertEquals(fundsInfo, state.fundsInfo)

    }


    @Test
    fun `getFundsInfo update error on IOException`(){
        val schemeCode = 1
        val response = Resource.Error<FundsInfo>(IOExceptionMessage)

        coEvery { savedStateHandle.get<String>(PARAM_SCHEME_CODE) } returns "1"
        coEvery { getFundsInfoUseCase(schemeCode) } returns response

        val viewModel = FundsInfoViewModel(getFundsInfoUseCase, savedStateHandle)

        val state = viewModel.fundsInfo.value
        assertEquals(false, state.isLoading)
        assertEquals(IOExceptionMessage, state.error)
    }

    @Test
    fun `getFundsInfo update error on Exception`(){
        val schemeCode = 1
        val response = Resource.Error<FundsInfo>(ErrorMessage)

        coEvery { savedStateHandle.get<String>(PARAM_SCHEME_CODE) } returns "1"
        coEvery { getFundsInfoUseCase(schemeCode) } returns response

        val viewModel = FundsInfoViewModel(getFundsInfoUseCase, savedStateHandle)

        val state = viewModel.fundsInfo.value
        assertEquals(false, state.isLoading)
        assertEquals(ErrorMessage, state.error)
    }
}