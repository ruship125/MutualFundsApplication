package com.example.mutualfundsapplicaiton.data.remote

import com.example.mutualfundsapplicaiton.data.remote.Dto.FundsInfoDto
import com.example.mutualfundsapplicaiton.data.remote.Dto.FundsListDto
import retrofit2.http.GET
import retrofit2.http.Path

interface MFapi {

    @GET("/mf")
    suspend fun getFundsList(): List<FundsListDto>

    @GET("/mf/{schemeCode}")
    suspend fun getFundsInfo(@Path("schemeCode")schemeCode: Int): FundsInfoDto

}