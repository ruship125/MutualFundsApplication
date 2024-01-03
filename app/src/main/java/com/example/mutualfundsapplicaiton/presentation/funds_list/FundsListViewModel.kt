package com.example.mutualfundsapplicaiton.presentation.funds_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mutualfundsapplicaiton.common.Resource
import com.example.mutualfundsapplicaiton.domain.model.FundsList
import com.example.mutualfundsapplicaiton.domain.use_case.GetFundsListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FundsListViewModel @Inject constructor(
    private val getFundsListUseCase : GetFundsListUseCase
): ViewModel() {

    private val _fundslist = mutableStateOf(FundsListState())
    val fundslist: State<FundsListState> = _fundslist

    init {
        getFundsList()
    }


    private fun getFundsList(){
        viewModelScope.launch {
            getFundsListUseCase().collect {result ->
                when (result) {
                    is Resource.Success -> {
                        _fundslist.value = FundsListState(
                            fundsList = result.data as List<FundsList>,
                        )
                    }
                    is Resource.Loading -> {
                        _fundslist.value = FundsListState(
                            isLoading = true
                        )
                    }
                    is Resource.Error -> {
                        _fundslist.value = FundsListState(
                            error = result.message!!
                        )
                    }
                }
            }
        }
    }

//    private fun getFundsList(){
//        viewModelScope.launch {
//            val result = getFundsListUseCase.invoke()
//            when (result) {
//                is Resource.Success<*> -> {
//                    _fundslist.value = FundsListState(
//                        fundsList = result.data as List<FundsList>,
//                    )
//                }
//                is Resource.Loading<*> -> {
//                    _fundslist.value = FundsListState(
//                        isLoading = true
//                    )
//                }
//                is Resource.Error<*> -> {
//                    _fundslist.value = FundsListState(
//                        error = result.message!!
//                    )
//                }
//            }
//        }
//    }

}