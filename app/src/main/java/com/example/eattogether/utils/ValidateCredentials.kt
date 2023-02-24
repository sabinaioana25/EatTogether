package com.example.eattogether.utils

import android.util.*
import com.google.android.material.textfield.TextInputLayout

fun validEmail(email: String): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

fun validPassword(password: String): Boolean {
    return password.isNotEmpty() && password.length > 8
    return (password.matches(".*[A-Z].*".toRegex()))
    return (password.matches(".*[a-z].*".toRegex()))
    return (password.matches(".*[@#\$%^&+=].*".toRegex()))
}