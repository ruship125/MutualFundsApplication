package com.example.mutualfundsapplicaiton.data.repository

import com.example.mutualfundsapplicaiton.common.Resource
import com.example.mutualfundsapplicaiton.data.remote.dto.toFundsInfo
import com.example.mutualfundsapplicaiton.data.remote.dto.toFundsList
import com.example.mutualfundsapplicaiton.data.remote.MFapi
import com.example.mutualfundsapplicaiton.domain.model.FundsInfo
import com.example.mutualfundsapplicaiton.domain.model.FundsList
import com.example.mutualfundsapplicaiton.domain.repository.MutualFundsRepository
import com.example.mutualfundsapplicaiton.common.Constants.IOExceptionMessage
import com.example.mutualfundsapplicaiton.common.Constants.ErrorMessage
import java.io.IOException
import javax.inject.Inject


class MutualFundsRepositoryImpl @Inject constructor(
    private val api: MFapi
): MutualFundsRepository {

    private suspend fun <T> callApi(apiCall: suspend () -> T): Resource<T> {
        return try {
            val result = apiCall.invoke()
            Resource.Success(result)
        } catch (e : IOException) {
            e.printStackTrace()
            Resource.Error(IOExceptionMessage)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(ErrorMessage)
        }
    }

    override suspend fun getFundsList() : Resource<List<FundsList>> {
        return callApi { api.getFundsList().map { it.toFundsList() } }
    }


    override suspend fun getFundsInfo(schemeCode : Int) : Resource<FundsInfo> {
        return callApi { api.getFundsInfo(schemeCode).toFundsInfo() }
    }

}
