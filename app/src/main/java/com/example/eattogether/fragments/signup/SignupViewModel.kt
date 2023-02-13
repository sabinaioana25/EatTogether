package com.example.eattogether.fragments.signup

import android.app.Application
import androidx.lifecycle.*
import timber.log.*
import com.google.firebase.auth.*

class SignupViewModel(
    val app: Application
) : AndroidViewModel(app) {

    private val _navigateToLogin = MutableLiveData<Boolean>()
    val navigateToLogin: LiveData<Boolean> get() = _navigateToLogin

    fun authSignUp(
        email: String, password: String
    ) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(
            email, password
        ).addOnCompleteListener {
            if (!it.isSuccessful) {
                Timber.i("created user successfully + ${FirebaseAuth.getInstance().currentUser}" + "$email" + "$password")
                return@addOnCompleteListener
            }
        }
            .addOnFailureListener {
            Timber.i("Failed to create user: ${it.message}")
            return@addOnFailureListener
        }
    }

    fun startedSignupNavigation() {
        _navigateToLogin.value = true
    }

    fun stoppedSignupNavigation() {
        _navigateToLogin.value = false
    }
}