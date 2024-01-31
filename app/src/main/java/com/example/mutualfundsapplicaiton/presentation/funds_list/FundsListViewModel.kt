package com.example.mutualfundsapplicaiton.presentation.funds_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mutualfundsapplicaiton.common.Resource
import com.example.mutualfundsapplicaiton.domain.model.FundsList
import com.example.mutualfundsapplicaiton.domain.use_case.funds_list_case.GetFundsListUseCase
import com.example.mutualfundsapplicaiton.domain.use_case.funds_list_case.SearchQueryListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FundsListViewModel @Inject constructor(
    private val getFundsListUseCase : GetFundsListUseCase,
    private val searchQueryListUseCase : SearchQueryListUseCase
): ViewModel() {

    private val _fundsList = mutableStateOf(FundsListState())
    val fundsList: State<FundsListState> = _fundsList

    private val _searchQuery = mutableStateOf("")
    val searchQuery: State<String> = _searchQuery

    private var saveFundsList: List<FundsList> = emptyList()

    init {
        getFundsList()
    }


    private fun getFundsList(){
        viewModelScope.launch {
            getFundsListUseCase().collect {result ->
                when (result) {
                    is Resource.Success -> {
                            _fundsList.value = FundsListState(
                                isLoading = false,
                                fundsList = result.data
                            )
                            saveFundsList = result.data ?: emptyList()
                    }
                    is Resource.Error -> {
                        _fundsList.value = FundsListState(
                            isLoading = false,
                            error = result.message.toString()
                        )
                    }
                }
            }
        }
    }

    internal fun searchList(query: String) {
        _searchQuery.value = query
        when (val result = searchQueryListUseCase(query, saveFundsList)) {
            is Resource.Success -> {
                _fundsList.value = FundsListState(
                    isLoading = false,
                    fundsList = result.data
                )
            }
            is Resource.Error -> {
                _fundsList.value = FundsListState(
                    isLoading = false,
                    error = result.message
                )
            }
        }
    }
}