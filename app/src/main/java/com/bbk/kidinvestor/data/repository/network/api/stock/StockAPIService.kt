package com.bbk.kidinvestor.data.repository.network.api.stock

import com.bbk.kidinvestor.ui.search.model.Stock
import retrofit2.http.GET


interface StockAPIService {

    @GET("/stocks")
    suspend fun getAllStocks(): List<Stock>

}