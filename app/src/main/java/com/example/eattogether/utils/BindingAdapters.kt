package com.example.eattogether.utils

import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text
import java.util.*

@BindingAdapter("bindSignupData")
fun ConstraintLayout.bindSignupData(userData: UserData) {
    (getChildAt(1) as TextView).text =
        userData.email.trim()
    (getChildAt(2) as TextView).text =
        userData.password.trim()
}

