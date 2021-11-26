package com.example.halanchallenge.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.halanchallenge.R
import com.example.halanchallenge.data.model.Profile
import com.example.halanchallenge.databinding.LoginFragmentBinding
import com.example.halanchallenge.util.*
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
            if(viewModel.validateFields()){
                loginUser()
            }
        }

        return binding.root
    }

    private fun setupTextChangeListeners() {
        val username = binding.usernameEt.text.toString().trim()
            viewModel.username = username

        val passwordEt = binding.usernameEt.text.toString().trim()
            viewModel.password = passwordEt

    }


    private fun loginUser() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            val loginResponse = viewModel.userLogin()
            val bundle =
                bundleOf("PROFILE" to loginResponse.profile, "TOKEN" to loginResponse.token)
            try {
                if (loginResponse.profile != null) {
                    viewModel.userLogin()
                    findNavController().navigate(
                        R.id.action_loginFragment_to_productsListFragment,
                        bundle
                    )
                }
            } catch (e: ApiException) {
                e.printStackTrace()
            } catch (e: NoInternetException) {
                e.printStackTrace()
            }

        }
    }


//    private fun loginUser() {
//        val username = binding.usernameEt.text.toString().trim()
//        val password = binding.passwordEt.text.toString().trim()
//
//        lifecycleScope.launch {
//            try {
//                val loginResponse = viewModel.userLogin(username, password)
//                val bundle =
//                    bundleOf("PROFILE" to loginResponse.profile, "TOKEN" to loginResponse.token)
//                if (loginResponse.profile != null) {
//                    findNavController().navigate(
//                        R.id.action_loginFragment_to_productsListFragment,
//                        bundle
//                    )
//                } else {
//                    Toast.makeText(activity, loginResponse.token, Toast.LENGTH_SHORT).show()
//                }
//            } catch (e: ApiException) {
//                e.printStackTrace()
//            } catch (e: NoInternetException) {
//                e.printStackTrace()
//            }
//        }
//    }


}