package com.bbk.kidinvestor.ui.login

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.liveData
import com.bbk.kidinvestor.R
import com.bbk.kidinvestor.data.repository.Repository
import com.bbk.kidinvestor.ui.login.model.LoginDTO
import com.bbk.kidinvestor.utils.Resource

class LoginViewModel(val repository: Repository, application: Application) :
    AndroidViewModel(application) {


    fun login(email: String, password: String) =
        liveData {
            emit(Resource.loading(data = null))
            try {
                val loginDTO =
                    LoginDTO(id = null, email = email, password = password, roleId = 1, name = null)
                emit(Resource.success(data = repository.login(loginDTO)))
            } catch (exception: Exception) {
                emit(Resource.error(data = null, message = exception.message ?: "Login Failed!"))
            }
        }

    fun isLoggedIn(): List<String> {
        val sharedPreferences = getApplication<Application>().getSharedPreferences(
            getApplication<Application>().getString(R.string.preference_file_key),
            Context.MODE_PRIVATE
        )

        val email = sharedPreferences.getString("email", null)
        val password = sharedPreferences.getString("password", null)
        return if (email != null && email.isNotBlank() && password != null && password.isNotBlank()) {
            listOf(email, password)
        } else {
            listOf()
        }
    }

    fun setLoginData(email: String, password: String) {

        val sharedPreferences = getApplication<Application>().getSharedPreferences(
            getApplication<Application>().getString(R.string.preference_file_key),
            Context.MODE_PRIVATE
        );

        sharedPreferences.edit().apply() {
            putString("email", email)
            commit()
            putString("password", password)
            commit()
        }
    }

    override fun onCleared() {
        super.onCleared()
    }


}