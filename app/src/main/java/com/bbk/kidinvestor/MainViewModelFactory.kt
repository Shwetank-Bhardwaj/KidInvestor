package com.bbk.kidinvestor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bbk.kidinvestor.data.repository.DataRepository
import com.bbk.kidinvestor.data.repository.network.NetworkManagerImpl

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory() :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(
                repository = DataRepository(
                    networkManager = NetworkManagerImpl()
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}
