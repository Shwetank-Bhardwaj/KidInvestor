package com.bbk.kidinvestor.ui.login.model

data class LoginDTO(
    var id: Int?,
    var email: String,
    var password: String,
    var name: String?,
    var roleId: Int
)