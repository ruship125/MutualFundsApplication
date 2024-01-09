package com.example.mutualfundsapplicaiton.data.repository

import com.example.mutualfundsapplicaiton.common.Resource
import com.example.mutualfundsapplicaiton.data.remote.Dto.toFundsInfo
import com.example.mutualfundsapplicaiton.data.remote.Dto.toFundsList
import com.example.mutualfundsapplicaiton.data.remote.MFapi
import com.example.mutualfundsapplicaiton.domain.model.FundsInfo
import com.example.mutualfundsapplicaiton.domain.model.FundsList
import com.example.mutualfundsapplicaiton.domain.repository.MutualFundsRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.net.ssl.SSLHandshakeException


class MutualFundsRepositoryImpl @Inject constructor(
    private val api: MFapi
): MutualFundsRepository {

    override suspend fun getFundsList() : Resource<List<FundsList>> {
        Resource.Loading(true)
        return try {
            val result = api.getFundsList()
            Resource.Success(result.map { it.toFundsList() })
        } catch (e : IOException) {
            e.printStackTrace()
            Resource.Error("Couldn't load data, try again")
        } catch (e : HttpException) {
            e.printStackTrace()
            Resource.Error("Check Internet Connection")
        }
    }

    override suspend fun getFundsInfo(schemeCode : Int) : Resource<FundsInfo> {
        Resource.Loading(true)
        return try {
            val result = api.getFundsInfo(schemeCode)
            Resource.Success(result.toFundsInfo())
        } catch (e : IOException) {
            e.printStackTrace()
            Resource.Error("Couldn't load data, try again")
        } catch (e : HttpException) {
            e.printStackTrace()
            Resource.Error("Check Internet Connection")
        }
    }
}