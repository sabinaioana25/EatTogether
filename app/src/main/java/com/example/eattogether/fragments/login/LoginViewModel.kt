package com.example.eattogether.fragments.login

import android.app.*
import androidx.lifecycle.*
import com.google.firebase.auth.FirebaseAuth
import timber.log.*

class LoginViewModel(
    val app: Application
) : AndroidViewModel(app) {

    private val _navigate = MutableLiveData<Boolean>()
    val navigate: LiveData<Boolean> get() = _navigate

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

    fun startedLoginNavigation() {
        _navigate.value = true
    }

    fun stoppedLoginNavigation() {
        _navigate.value = false
    }
}