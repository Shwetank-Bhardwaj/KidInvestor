package com.bbk.kidinvestor.data.repository

import com.bbk.kidinvestor.data.repository.network.NetworkManager
import com.bbk.kidinvestor.ui.login.model.LoginDTO
import com.bbk.kidinvestor.ui.search.model.Stock

class DataRepository(private val networkManager: NetworkManager) : Repository {

    override suspend fun login(loginDTO: LoginDTO) = networkManager.login(loginDTO)
    override suspend fun getAllStocks(): List<Stock> = networkManager.getAllStocks()
}