package com.bbk.kidinvestor.data.repository.network.api.login

import com.bbk.kidinvestor.ui.login.model.LoginDTO
import com.bbk.kidinvestor.ui.login.model.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST


interface LoginAPIService {

    @POST("/login")
    suspend fun login(@Body loginDTO: LoginDTO): LoginResponse

}