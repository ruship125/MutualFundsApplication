package com.example.mutualfundsapplicaiton.presentation.funds_list

import androidx.compose.material.TextField
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mutualfundsapplicaiton.common.Resource
import com.example.mutualfundsapplicaiton.domain.model.FundsList
import com.example.mutualfundsapplicaiton.domain.use_case.GetFundsListUseCase
import com.example.mutualfundsapplicaiton.domain.use_case.SearchQueryListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FundsListViewModel @Inject constructor(
    private val getFundsListUseCase : GetFundsListUseCase,
    private val searchQueryListUseCase : SearchQueryListUseCase
): ViewModel() {

    private val _fundslist = mutableStateOf(FundsListState())
    val fundslist: State<FundsListState> = _fundslist

    val searchQuery = mutableStateOf("")


    init {
        getFundsList()
    }


    private fun getFundsList(){
        viewModelScope.launch {
            getFundsListUseCase().collect {result ->
                when (result) {
                    is Resource.Success -> {
                        _fundslist.value = FundsListState(
                            isLoading = false,
                            fundsList = result.data as List<FundsList>
                        )
                    }
                    is Resource.Loading -> {
                        /* todo() */
                    }
                    is Resource.Error -> {
                        _fundslist.value = FundsListState(
                            isLoading = false,
                            error = result.message.toString()
                        )
                    }
                }
            }
        }
    }

    fun searchQuerylist(){
        viewModelScope.launch {
            searchQueryListUseCase(searchQuery.value).collect {result ->
                when (result) {
                    is Resource.Success -> {
                        _fundslist.value = FundsListState(
                            isLoading = false,
                            fundsList = result.data!!
                        )
                    }
                    is Resource.Loading -> {
                        /* todo() */
                    }
                    is Resource.Error -> {
                        _fundslist.value = FundsListState(
                            isLoading = false,
                            error = result.message.toString()
                        )
                    }
                }
            }
        }
    }
}