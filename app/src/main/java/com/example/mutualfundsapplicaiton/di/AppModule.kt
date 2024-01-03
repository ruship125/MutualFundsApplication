package com.example.mutualfundsapplicaiton.di

import com.example.mutualfundsapplicaiton.common.Constants
import com.example.mutualfundsapplicaiton.data.remote.MFapi
import com.example.mutualfundsapplicaiton.data.repository.MutualFundsRepositoryImpl
import com.example.mutualfundsapplicaiton.domain.repository.MutualFundsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.inject.Singleton
import javax.net.ssl.*


@Module
@InstallIn(SingletonComponent::class)
object AppModule{

    @Provides
    @Singleton
    fun provideMFapi(): MFapi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MFapi::class.java)
    }


    @Provides
    @Singleton
    fun provideMutualFundsRepository(api: MFapi): MutualFundsRepository {
        return MutualFundsRepositoryImpl(api)
    }
}