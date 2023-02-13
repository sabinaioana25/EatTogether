package com.example.eattogether.utils

data class UserData(
    var email: String,
    var password: String
)

fun UserData.isCompleted() =
    email != "" && password != ""