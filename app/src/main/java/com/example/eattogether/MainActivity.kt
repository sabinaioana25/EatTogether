package com.example.eattogether

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.navigation.*
import androidx.navigation.ui.NavigationUI
import com.example.eattogether.databinding.ActivityMainBinding
import com.example.eattogether.utils.*
import timber.log.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.plant(Timber.DebugTree())

        @Suppress("UNUSED_VARIABLE")
        binding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        val navController =
            this.findNavController(R.id.navHostFragment)
        NavigationUI.setupActionBarWithNavController(
            this, navController
        )
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController =
            this.findNavController(R.id.navHostFragment)
        return navController.navigateUp()
    }

//    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
//        if (ev != null) {
//            currentFocus?.removeFocusAndHideKeyboard(
//                this,
//                ev
//            )
//        }
//        return super.dispatchTouchEvent(ev)
//    }
}