package com.example.eattogether.fragments.login

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.*
import androidx.lifecycle.*
import androidx.navigation.fragment.*
import com.example.eattogether.R
import com.example.eattogether.databinding.*
import com.example.eattogether.utils.*
import timber.log.*

@Suppress("COMPATIBILITY_WARNING", "DEPRECATION")
class LoginFragment : Fragment() {

    private lateinit var loginViewModel: LoginViewModel

    // initialize viewBinding
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
        loginViewModel.navigateToMap.observe(viewLifecycleOwner) { it ->
            if (it) {
                if (validEmail(binding.editTextEmailLogin.text.toString().trim()) &&
                    validPassword(binding.editTextPasswordLogin.text.toString().trim())) {
                    loginViewModel.authLogIn(
                        binding.editTextEmailLogin.text.toString().trim(),
                        binding.editTextPasswordLogin.text.toString().trim()
                    )
                    findNavController().navigate(R.id.action_loginFragment_to_mapFragment)
                    loginViewModel.onFragmentNagivated()
                } else Timber.i("incorrect email or password")
            }
            loginViewModel.navigateToSignup.observe(viewLifecycleOwner) {
                if (it) {
                    findNavController().navigate(R.id.action_loginFragment_to_signupFragment)
                    loginViewModel.onFragmentNagivated()
                }
            }
        }
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}