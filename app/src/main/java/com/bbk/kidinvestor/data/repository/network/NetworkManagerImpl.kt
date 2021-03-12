package com.bbk.kidinvestor.data.repository.network

import com.bbk.kidinvestor.data.repository.network.api.login.LoginAPIHelper
import com.bbk.kidinvestor.data.repository.network.api.stock.StockAPIHelper
import com.bbk.kidinvestor.ui.login.model.LoginDTO

class NetworkManagerImpl : NetworkManager {

    override suspend fun login(loginDTO: LoginDTO) =
        LoginAPIHelper(RetrofitBuilder.loginAPIService).login(loginDTO)

    override suspend fun getAllStocks() =
        StockAPIHelper(RetrofitBuilder.stockAPIService).getAllStocks()

}