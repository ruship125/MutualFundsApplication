package com.example.mutualfundsapplicaiton.presentation.funds_info

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mutualfundsapplicaiton.common.Constants
import com.example.mutualfundsapplicaiton.common.Resource
import com.example.mutualfundsapplicaiton.domain.use_case.GetFundsInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FundsInfoViewModel @Inject constructor(
    private val getFundsInfoUseCase : GetFundsInfoUseCase,
    private val savedStateHandle : SavedStateHandle
): ViewModel(){

    private val _fundsInfo = mutableStateOf(FundsInfoState())
    val fundsInfo: State<FundsInfoState> = _fundsInfo

    init {
        savedStateHandle.get<String>(Constants.PARAM_SCHEME_CODE)?.let { schemeCode ->
            getFundsInfo(schemeCode.toInt())
        }
    }

    private fun getFundsInfo(schemeCode: Int){
        viewModelScope.launch {
            when(val res = getFundsInfoUseCase(schemeCode)){
                is Resource.Success -> {
                    _fundsInfo.value = FundsInfoState(isLoading = false, fundsInfo = res.data)
                }
                is Resource.Error -> {
                    _fundsInfo.value = FundsInfoState(isLoading = false, error = res.message.toString())
                }
                is Resource.Loading -> TODO()
            }
        }
    }

}