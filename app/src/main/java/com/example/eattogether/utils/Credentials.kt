package com.example.eattogether.utils

import android.text.TextUtils
import android.util.*
import timber.log.*

class Credentials {

    val email: String = ""
    val password: String = ""

    fun checkEditTextNotEmpty(
        email: String,
        password: String
    ) {
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(
                password
            )
        ) {
            EatTogetherAction.TOAST_EMPTY
        } else {
            Timber.i("edittext is not null")
        }
    }

    fun checkEmailPattern(email: String) {
        if (!Patterns.EMAIL_ADDRESS.matcher(email)
                .matches()
        ) {
            EatTogetherAction.TOAST_EMPTY
        }
    }

    fun checkPasswordPattern(passowrd: String): String {
        if (passowrd.length < 8) return "Must be at least 8 characters long"
        if (!passowrd.contains(".*[A-Z].*".toRegex())) return "Must contain 1 upper-case character"
        if (!passowrd.contains(".*[a-z].*".toRegex())) return "Must contain 1 lower-case character"
        if (!passowrd.contains(".*[!@#£$%^&*()+=_].*".toRegex())) return "Must contain 1 special character (@#$%^&+=)"
        return "password is acceptable"
    }
}