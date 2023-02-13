package com.example.eattogether.fragments.login

import android.os.*
import android.text.*
import android.util.*
import android.view.*
import android.widget.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.*
import androidx.lifecycle.*
import androidx.navigation.*
import androidx.navigation.fragment.*
import com.example.eattogether.R
import com.example.eattogether.databinding.*
import com.example.eattogether.utils.*
import com.google.firebase.auth.*
import timber.log.*
import kotlin.math.*

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@Suppress("COMPATIBILITY_WARNING")
class LoginFragment : Fragment() {

    private lateinit var loginViewModel: LoginViewModel

    // initialize viewbinding
    private var _binding: FragmentLoginBinding? = null
    private val binding: FragmentLoginBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        loginViewModel = ViewModelProvider(
            this, ViewModelFactory(
                requireActivity().application
            )
        )[LoginViewModel::class.java]

        // Inflate the login layout for this fragment
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            varLogInViewModel = loginViewModel
        }
        loginViewModel.navigate.observe(viewLifecycleOwner) {
            if (it) {
                if (validEmail(
                        binding.etEmail.text.toString().trim()
                    ) && validPassword(binding.etPassword.toString().trim())) {
                    loginViewModel.authLogIn(
                        binding.etEmail.text.toString().trim(),
                        binding.etPassword.text.toString().trim()
                    )
                    findNavController().navigate(R.id.action_loginFragment_to_mapFragment)
                    loginViewModel.stoppedLoginNavigation()
                }
//                findNavController().navigate(R.id.action_loginFragment_to_signupFragment)
//                loginViewModel.stoppedLoginNavigation()
            }
        }
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}