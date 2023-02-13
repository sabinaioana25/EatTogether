package com.example.eattogether.utils

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.eattogether.fragments.login.LoginViewModel
import com.example.eattogether.fragments.signup.SignupViewModel

class ViewModelFactory(private val app: Application): ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>) : T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(app) as T
        } else if (modelClass.isAssignableFrom(SignupViewModel::class.java)) {
            return SignupViewModel(app) as T
        }
        throw java.lang.IllegalArgumentException("Unknown ViewModel class")
    }
}