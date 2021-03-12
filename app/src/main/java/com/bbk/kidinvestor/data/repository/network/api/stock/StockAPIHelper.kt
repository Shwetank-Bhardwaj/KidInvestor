package com.bbk.kidinvestor.data.repository.network.api.stock


class StockAPIHelper(private val stockAPIService: StockAPIService) {

    suspend fun getAllStocks() = stockAPIService.getAllStocks()

}