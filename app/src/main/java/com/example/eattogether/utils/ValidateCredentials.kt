package com.example.eattogether.utils

import android.util.*

fun validEmail(email: String): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

fun validPassword(password: String): Boolean {
    if (password.length > 8) return true
    if (password.matches(".*[A-Z].*".toRegex())) return true
    if (password.matches(".*[a-z].*".toRegex())) return true
    if (password.matches(".*[@#\$%^&+=].*".toRegex())) return true
    return false
}