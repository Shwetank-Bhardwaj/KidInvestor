package com.bbk.kidinvestor.data.repository.network.api.login

import com.bbk.kidinvestor.ui.login.model.LoginDTO


class LoginAPIHelper(private val loginAPIService: LoginAPIService) {

    suspend fun login(loginDTO: LoginDTO) = loginAPIService.login(loginDTO)

}