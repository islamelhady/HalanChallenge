package com.example.halanchallenge.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.halanchallenge.R
import com.example.halanchallenge.databinding.LoginFragmentBinding
import com.example.halanchallenge.util.ApiException
import com.example.halanchallenge.util.NoInternetException
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    private val viewModel: LoginViewModel by viewModel()
    private lateinit var binding: LoginFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LoginFragmentBinding.inflate(inflater)


        binding.loginButton.setOnClickListener {
            loginUser()
        }

        return binding.root
    }

    private fun loginUser() {
        val username = binding.usernameEt.text.toString().trim()
        val password = binding.passwordEt.text.toString().trim()

        lifecycleScope.launch {
            try {
                val loginResponse = viewModel.userLogin(username, password)
                if (loginResponse.profile != null) {
                    findNavController().navigate(R.id.action_loginFragment_to_productsListFragment)
                } else {
                    Toast.makeText(activity, loginResponse.token, Toast.LENGTH_SHORT).show()
                }
            } catch (e: ApiException) {
                e.printStackTrace()
            } catch (e: NoInternetException) {
                e.printStackTrace()
            }
        }
    }

}