package com.example.eattogether.fragments.signup

import android.os.Bundle
import android.text.*
import android.util.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.*
import androidx.navigation.fragment.*
import com.example.eattogether.R
import com.example.eattogether.databinding.FragmentSignupBinding
import com.example.eattogether.utils.*
import timber.log.*

/**
 * A simple [Fragment] subclass.
 * Use the [SignupFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SignupFragment : Fragment() {

    private lateinit var signupViewModel: SignupViewModel
    private var _binding: FragmentSignupBinding? = null
    private val binding: FragmentSignupBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        signupViewModel = ViewModelProvider(
            this, ViewModelFactory(
                requireActivity().application)
        )[SignupViewModel::class.java]

        // Inflate the signup layout for this fragment
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_signup, container, false)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            varSignUpViewModel = signupViewModel
        }
        signupViewModel.navigateToLogin.observe(viewLifecycleOwner) {
            if (it) {
                if (validEmail(
                        binding.editTextEmailSignup.text.toString().trim()
                    ) && validPassword(binding.editTextPasswordSignup.text.toString().trim())) {
                    signupViewModel.authSignUp(
                        binding.editTextEmailSignup.text.toString().trim(),
                        binding.editTextPasswordSignup.text.toString().trim()
                    )
                    findNavController().navigate(R.id.action_signupFragment_to_loginFragment)
                    signupViewModel.stoppedSignupNavigation()
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