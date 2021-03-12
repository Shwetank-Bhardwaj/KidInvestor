package com.bbk.kidinvestor.ui.login.model


data class LoginResponse(
    val displayName: String,
    val netWorth: Double,
    val accountBalance: Double,
    val email: String
)