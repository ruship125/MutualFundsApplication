package com.example.mutualfundsapplicaiton.presentation.funds_list

import com.example.mutualfundsapplicaiton.common.Constants.IOExceptionMessage
import com.example.mutualfundsapplicaiton.common.Resource
import com.example.mutualfundsapplicaiton.domain.model.FundsList
import com.example.mutualfundsapplicaiton.domain.use_case.funds_list_case.GetFundsListUseCase
import com.example.mutualfundsapplicaiton.domain.use_case.funds_list_case.SearchQueryListUseCase
import com.example.mutualfundsapplicaiton.common.Constants.SEARCH_FUND_NOT_FOUND
import com.example.mutualfundsapplicaiton.common.Constants.ErrorMessage
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class FundsListViewModelTest {

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

    private val getFundsListUseCase = mockk<GetFundsListUseCase>()
    private val searchQueryListUseCase = SearchQueryListUseCase()
    private val fundsList = listOf(
        FundsList(1, "Scheme 1"),
        FundsList(2,"Scheme 2")
    )



    @Test
    fun `getFundsList updates fundsList state`() = runBlockingTest {

        coEvery { getFundsListUseCase() } returns flowOf(Resource.Success(fundsList))

        val viewModel = FundsListViewModel(getFundsListUseCase, searchQueryListUseCase)

        val state = viewModel.fundsList.value
        assertEquals(false, state.isLoading)
        assertEquals(fundsList, state.fundsList)
    }

    @Test
    fun `getFundsList updates error state on IOException`() = runBlockingTest {

        val response = Resource.Error<List<FundsList>>(IOExceptionMessage)
        coEvery { getFundsListUseCase() } returns flowOf(response)

        val viewModel = FundsListViewModel(getFundsListUseCase, searchQueryListUseCase)

        val state = viewModel.fundsList.value
        assertEquals(false, state.isLoading)
        assertEquals(null, state.fundsList)
        assertEquals(IOExceptionMessage, state.error)
    }

    @Test
    fun `getFundsList updates error state on Exception`() = runBlockingTest {

        val response = Resource.Error<List<FundsList>>(ErrorMessage)
        coEvery { getFundsListUseCase() } returns flowOf(response)

        val viewModel = FundsListViewModel(getFundsListUseCase, searchQueryListUseCase)

        val state = viewModel.fundsList.value
        assertEquals(false, state.isLoading)
        assertEquals(null, state.fundsList)
        assertEquals(ErrorMessage, state.error)
    }

    @Test
    fun `searchList updates fundsList state`() = runBlockingTest {

        val query = "1"
        coEvery { getFundsListUseCase() } returns flowOf(Resource.Success(fundsList))

        val viewModel = FundsListViewModel(getFundsListUseCase, searchQueryListUseCase)
        viewModel.searchList(query)

        val state = viewModel.fundsList.value
        assertEquals(false, state.isLoading)
        assertEquals(listOf(FundsList(1,"Scheme 1")), state.fundsList)
    }

    @Test
    fun `searchList updates error state`() = runBlockingTest  {

        val query = "3"
        coEvery { getFundsListUseCase() } returns flowOf(Resource.Success(fundsList))

        val viewModel = FundsListViewModel(getFundsListUseCase, searchQueryListUseCase)
        viewModel.searchList(query)

        val state = viewModel.fundsList.value
        assertEquals(false, state.isLoading)
        assertEquals(SEARCH_FUND_NOT_FOUND, state.error)
    }
}
