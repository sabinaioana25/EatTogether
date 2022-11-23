package Fragments

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.eattogether.R
import com.example.eattogether.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.app

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {

    private var email: String = ""
    private var password: String = ""

    // initiatie firebase authentication instance
    private lateinit var auth: FirebaseAuth

    // initialize viewbinding
    private var _binding: FragmentLoginBinding? = null
    private val binding: FragmentLoginBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the login layout for this fragment
        _binding = FragmentLoginBinding.inflate(
            inflater, container, false
        )
//        val currentUser = auth.currentUser
//        if (currentUser != null) {
//            //navigate directly to map view
//            Toast.makeText(context, "already signed in", Toast.LENGTH_SHORT).show()
//        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        email = binding.etEmail.text.toString().trim()
        password = binding.etPassword.text.toString().trim()

        auth = FirebaseAuth.getInstance()
        binding.tvLinkSignup.setOnClickListener {
            view.findNavController().navigate(R.id.action_loginFragment_to_signupFragment)
        }

        binding.btnLogin.setOnClickListener {
            if (validateForm()) {
                if (email.isEmpty() || password.isEmpty())
                    return@setOnClickListener

                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val user = auth.currentUser
                            Log.d(TAG, "signIn successful with $user")
                            view.findNavController()
                                .navigate(R.id.action_loginFragment_to_mapFragment)
                        } else {
                            Toast.makeText(context, "authentication failed", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
            }
        }
    }

    private fun validateForm(): Boolean {
        var valid = true
        email = binding.etEmail.text.toString()
        password = binding.etPassword.text.toString()

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            binding.etEmail.error = "Required"
            binding.etPassword.error = "Required"
            valid = false
        } else {
            binding.etEmail.error = null
            binding.etPassword.error = null
        }
        return valid

    }

    companion object {
        private val TAG = LoginFragment::class.qualifiedName
    }
}