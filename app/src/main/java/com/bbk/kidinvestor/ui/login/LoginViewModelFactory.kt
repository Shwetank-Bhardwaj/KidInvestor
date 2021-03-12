package com.bbk.kidinvestor.ui.login

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bbk.kidinvestor.data.repository.DataRepository
import com.bbk.kidinvestor.data.repository.network.NetworkManagerImpl

@Suppress("UNCHECKED_CAST")
class LoginViewModelFactory(private val applicationContext: Application) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(
                application = applicationContext,
                repository = DataRepository(
                    networkManager = NetworkManagerImpl()
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}
