package com.example.eattogether.fragments.login

import android.app.*
import androidx.lifecycle.*
import androidx.navigation.*
import com.google.firebase.auth.FirebaseAuth
import timber.log.*

class LoginViewModel(
    val app: Application
) : AndroidViewModel(app) {

    private val _navigateToMap = MutableLiveData<Boolean>()
    val navigateToMap: LiveData<Boolean> get() = _navigateToMap
    private val _navigateToSignup = MutableLiveData<Boolean>()
    val navigateToSignup: LiveData<Boolean> get() = _navigateToSignup

    fun authLogIn(
        email: String, password: String
    ) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(
            email, password
        ).addOnCompleteListener {
            if (!it.isSuccessful) {
                Timber.i("logged in successfully: ${FirebaseAuth.getInstance().currentUser}" + "$email")
                return@addOnCompleteListener
            }
        }
            .addOnFailureListener {
                Timber.i("Failed to log in: ${it.message}")
                return@addOnFailureListener
            }
    }

    fun onButtonLoginClick() {
        _navigateToMap.value = true
    }

    fun onFragmentNagivated() {
        _navigateToMap.value = false
        _navigateToSignup.value = false
    }

    fun onButtonSignupClick() {
        _navigateToSignup.value = true
    }
}