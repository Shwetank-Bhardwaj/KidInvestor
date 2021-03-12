package com.bbk.kidinvestor.data.repository.network

import com.bbk.kidinvestor.ui.login.model.LoginDTO
import com.bbk.kidinvestor.ui.login.model.LoginResponse
import com.bbk.kidinvestor.ui.search.model.Stock


interface NetworkManager {

    suspend fun login(loginDTO: LoginDTO): LoginResponse
    suspend fun getAllStocks(): List<Stock>

}