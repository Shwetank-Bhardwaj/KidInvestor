package com.bbk.kidinvestor.data.repository

import com.bbk.kidinvestor.ui.login.model.LoginDTO
import com.bbk.kidinvestor.ui.login.model.LoginResponse
import com.bbk.kidinvestor.ui.search.model.Stock

interface Repository {

    suspend fun login(email: LoginDTO): LoginResponse
    suspend fun getAllStocks(): List<Stock>

}