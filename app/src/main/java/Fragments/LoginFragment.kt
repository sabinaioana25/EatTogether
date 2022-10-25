package Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.eattogether.R
import com.example.eattogether.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {

//    initiatie firebase authentication instance
    private lateinit var auth: FirebaseAuth

    // initialize viewbinding
    private var _binding: FragmentLoginBinding? = null
    private val binding: FragmentLoginBinding
        get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the login layout for this fragment
        _binding = FragmentLoginBinding.inflate(
            inflater, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            tvLinkSignup.setOnClickListener (
                Navigation.createNavigateOnClickListener(R.id.action_loginFragment_to_signupFragment)
            )
            btnLogin.setOnClickListener (
                Navigation.createNavigateOnClickListener(R.id.action_loginFragment_to_mapFragment)
            )
//            btnLogin.setOnClickListener {
//                val email = binding.etEmail.text.toString().trim()
//                val password = binding.etPassword.text.toString().trim()
//
//                auth.signInWithEmailAndPassword(email, password)
//                    .addOnCompleteListener(requireActivity()) { task ->
//                        if (task.isSuccessful) {
//
//                            // Sign in successfully
//                            Log.d(TAG,"signed in with $email and $password")
//                            val user = auth.currentUser
//                        }
//                        else {
//                            Log.d(TAG, "failed to sign in")
//                        }
//                    }
//            }
        }
    }

    companion object {
        private val TAG = LoginFragment::class.qualifiedName
    }
}