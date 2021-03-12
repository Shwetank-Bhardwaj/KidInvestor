package com.bbk.kidinvestor.data.repository.network

import com.bbk.kidinvestor.data.repository.network.api.login.LoginAPIService
import com.bbk.kidinvestor.data.repository.network.api.stock.StockAPIService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//import retrofit2.converter.moshi.MoshiConverterFactory


object RetrofitBuilder {

    private const val BASE_URL = "https://kid-investor-backend.appspot.com"

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
//            .addConverterFactory(MoshiConverterFactory.create())
            .build() //Doesn't require the adapter
    }

    val loginAPIService: LoginAPIService = getRetrofit().create(LoginAPIService::class.java)
    val stockAPIService: StockAPIService = getRetrofit().create(StockAPIService::class.java)

}