package Fragments

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.eattogether.MainActivity
import com.example.eattogether.R
import com.example.eattogether.databinding.FragmentLoginBinding
import com.example.eattogether.databinding.FragmentSignupBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.ktx.Firebase
import org.w3c.dom.Text
import kotlin.math.log

/**
 * A simple [Fragment] subclass.
 * Use the [SignupFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SignupFragment : Fragment() {

    // initialize firebase authentication instance
//    private lateinit var auth: FirebaseAuth

    // initialize view binding
    private var _binding: FragmentSignupBinding? = null
    private val binding: FragmentSignupBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the signup layout for this fragment
        _binding = FragmentSignupBinding.inflate(
            inflater, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSignup.setOnClickListener {
            val name = binding.etPersonName.text.toString().trim()
            val email = binding.etEmailAddress.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            val retypePassword = binding.etRetypePassword.text.toString().trim()

            createAccount(name, email, password)
            if (name.isEmpty() || email.isEmpty() || password.isEmpty() || retypePassword.isEmpty() )
                return@setOnClickListener

            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(requireActivity()) {task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "created user successfully + ${FirebaseAuth.getInstance().currentUser}")
                        view.findNavController().navigate(R.id.action_signupFragment_to_loginFragment)

                    }
                }
                .addOnFailureListener {
                    Log.d(TAG,"Failed to create user: ${it.message}")
                }
        }
    }

    private fun validateForm(): Boolean {
        var valid = true
        val name = binding.etPersonName.text.toString()
        val email = binding.etEmailAddress.text.toString()
        val password = binding.etPassword.text.toString()
        val retypePassword = binding.etRetypePassword.text.toString()

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || password.length <= 8 || retypePassword != password)
        {
            binding.etPersonName.error = "Required"
            binding.etEmailAddress.error = "Required"
            binding.etPassword.error = "Required & must be 8 char long"
            binding.etRetypePassword.error = "Password must match"
            valid = false
        } else {
            binding.etPersonName.error = null
            binding.etEmailAddress.error = null
            binding.etPassword.error = null
            binding.etRetypePassword.error = null
        }
        return valid
    }

    private fun createAccount(name:String, email: String, password: String) {
        Log.d(TAG, "createAccount:$name + $email " + "type: $password")
        if (!validateForm())
            Log.d(TAG,"is not valid")
//            return
    }

    companion object {
        private val TAG = SignupFragment::class.qualifiedName
    }
}