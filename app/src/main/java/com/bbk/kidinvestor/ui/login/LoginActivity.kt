package com.bbk.kidinvestor.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bbk.kidinvestor.MainActivity
import com.bbk.kidinvestor.databinding.ActivityLoginBinding
import com.bbk.kidinvestor.utils.Status

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel = ViewModelProvider(
            this,
            LoginViewModelFactory(application)
        ).get(LoginViewModel::class.java)

        binding.login.setOnClickListener {
            viewModel.setLoginData(
                email = binding.username.text.toString(),
                password = binding.password.text.toString()
            )
            login()
        }
        login()
    }

    private fun login() {
        if (viewModel.isLoggedIn().isEmpty()) {
            return
        }
        val email: String = viewModel.isLoggedIn()[0]
        val password: String = viewModel.isLoggedIn()[1]
        viewModel.login(email, password).observe(this@LoginActivity, {
            when (it.status) {
                Status.SUCCESS -> {
                    showMessage("Welcome ${it.data?.displayName} Login Successful")

                    binding.loading.visibility = View.GONE

                    val intent = Intent(applicationContext, MainActivity::class.java)
                    intent.apply {
                        putExtra("netWorth", it.data?.netWorth)
                        putExtra("accountBalance", it.data?.accountBalance)
                    }
                    startActivity(intent)
                    finish();
                }
                Status.LOADING -> {
                    binding.loading.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    showMessage(it.message ?: "Login Failed")
                    binding.loading.visibility = View.GONE
                }
            }
        })
    }

    private fun showMessage(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
    }

}