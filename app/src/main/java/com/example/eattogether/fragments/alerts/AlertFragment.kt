package com.example.eattogether.fragments.alerts

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.eattogether.databinding.FragmentAlertBinding

class AlertFragment : DialogFragment() {

    private var _binding: FragmentAlertBinding? = null
    private val binding: FragmentAlertBinding
        get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAlertBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setMessage("Account successfully created")
                .setPositiveButton("Back to Login", DialogInterface.OnClickListener
                    { dialog, id ->
                        Toast.makeText(it, "Show alert dialog", Toast.LENGTH_SHORT).show()
                        // Action back to LoginFragment
                    })
                builder.create()
        } ?: throw IllegalStateException ("Activity cannot be null")
    }
}