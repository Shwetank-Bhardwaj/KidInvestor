package com.bbk.kidinvestor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.bbk.kidinvestor.data.repository.DataRepository
import com.bbk.kidinvestor.ui.search.model.Stock
import com.bbk.kidinvestor.utils.Resource

class MainViewModel(val repository: DataRepository) : ViewModel() {

    private val _stockList = MutableLiveData<List<Stock>>()
    val stockList: LiveData<List<Stock>> = _stockList

    fun getStockList(newText: String) {
        liveData {
            emit(Resource.loading(data = null))
            try {
                val queryStocks = repository.getAllStocks()
                _stockList.postValue(queryStocks)
                emit(Resource.success(data = queryStocks))
            } catch (e: Exception) {
                emit(Resource.error(data = null, message = e.message ?: "Error in getting data"))
            }
        }
    }

    fun getAllStockList() =
        liveData {
            emit(Resource.loading(data = null))
            try {
                val allStocks = repository.getAllStocks()
                _stockList.postValue(allStocks)
                emit(Resource.success(data = allStocks))
            } catch (e: Exception) {
                emit(Resource.error(data = null, message = e.message ?: "Error in getting data"))
            }
        }

}