package com.example.eattogether.utils

import android.content.Context
import android.graphics.Rect
import android.text.*
import android.util.*
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.example.eattogether.fragments.signup.*
import timber.log.*
import com.example.eattogether.fragments.signup.*
import com.google.android.material.textfield.*

// Fragment
fun Fragment.navigate(directions: NavDirections) =
    findNavController().navigate(directions)

fun Fragment.toast(message: CharSequence) =
    Toast.makeText(
        context, message, Toast.LENGTH_SHORT
    ).show()


//fun View.removeFocusAndHideKeyboard(
//    context: Context, event: MotionEvent
//) {
//    if (event.action == MotionEvent.ACTION_DOWN) {
//        if (this is EditText) {
//            val outRect = Rect()
//            getGlobalVisibleRect(outRect)
//            if (!outRect.contains(
//                    event.rawX.toInt(),
//                    event.rawY.toInt()
//                )
//            ) {
//                clearFocus()
//                val imm: InputMethodManager =
//                    context.getSystemService(
//                        Context.INPUT_METHOD_SERVICE
//                    ) as InputMethodManager
//                imm.hideSoftInputFromWindow(
//                    windowToken, 0
//                )
//            }
//        }
//    }
//}