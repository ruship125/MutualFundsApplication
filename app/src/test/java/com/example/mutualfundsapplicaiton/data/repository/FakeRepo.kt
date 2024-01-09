package com.example.mutualfundsapplicaiton.data.repository

import com.example.mutualfundsapplicaiton.common.Resource
import com.example.mutualfundsapplicaiton.data.remote.Dto.Data
import com.example.mutualfundsapplicaiton.data.remote.Dto.Meta
import com.example.mutualfundsapplicaiton.domain.model.FundsInfo
import com.example.mutualfundsapplicaiton.domain.model.FundsList
import com.example.mutualfundsapplicaiton.domain.repository.MutualFundsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeRepo: MutualFundsRepository {

    //Data example
    private val fundslist = mutableListOf<FundsList>()

    private val datalist = mutableListOf<Data>()
    private val meta = Meta("house","category",0,"name","type")
    private val fundsInfo = FundsInfo(datalist,meta)

    override suspend fun getFundsList() : Resource<List<FundsList>> {
        ('a'..'j').forEachIndexed { index, c ->
            fundslist.add(index, FundsList(index, c.toString()))
        }
        return Resource.Success(fundslist)
    }

    override suspend fun getFundsInfo(schemeCode : Int): Resource<FundsInfo>{
        return Resource.Success(fundsInfo)
    }
}