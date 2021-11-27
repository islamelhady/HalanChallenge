package com.example.halanchallenge.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.halanchallenge.R
import com.example.halanchallenge.data.model.LoginResponse
import com.example.halanchallenge.databinding.LoginFragmentBinding
import com.example.halanchallenge.util.*
import kotlinx.coroutines.flow.collect
import org.koin.android.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    private val viewModel: LoginViewModel by viewModel()
    private lateinit var binding: LoginFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LoginFragmentBinding.inflate(inflater)

        setupTextChangeListeners()
        setupLoginEvents()
        processLoginResult()

        binding.loginButton.setOnClickListener {
            if(viewModel.validateFields()){
                viewModel.loginUser()
            }
        }
        return binding.root
    }

    private fun setupTextChangeListeners() {
        binding.username.addTextChangedListener { _username ->
            viewModel.username = _username.toString().trim()
        }
        binding.password.addTextChangedListener { _password ->
            viewModel.password = _password.toString().trim()
        }
    }

    private fun processLoginResult(){
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.userResource?.collect { userResource ->
                when (userResource.status) {
                    Status.LOADING -> {
                        binding.progressBar.makeItVisible()
                    }
                    Status.SUCCESS -> {
                        binding.progressBar.makeItInVisible()
                        userResource.data?.let { _user ->
                            viewModel.onSuccessfulLogin(_user)
                            Toast.makeText(activity, "Login", Toast.LENGTH_SHORT).show()
                        }
                    }
                    Status.ERROR -> {
                        binding.progressBar.makeItInVisible()
                        userResource.message?.let {
                            viewModel.onShowErrorMessage(userResource.message)
                            Toast.makeText(activity, "Error", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }

    private fun setupLoginEvents() {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.loginEventChannel.collect { event ->
                when(event)
                {

                    is LoginViewModel.LoginEvent.ShowErrorMessage ->  {
                        navigateToErrorBottomDialogFragment(event.message)
                    }
                    is LoginViewModel.LoginEvent.LoginCompletedEvent ->  {
                        navigateToShoppingListsFragment(event.response)
                    }
                }
            }
        }
    }


    // Navigate
    private fun navigateToShoppingListsFragment(response: LoginResponse) {
        findNavController().popBackStack(R.id.loginFragment, true)
        val action = LoginFragmentDirections.actionLoginFragmentToProductsListFragment(response)
        findNavController().navigate(action)
    }

    private fun navigateToErrorBottomDialogFragment(errorMessage: String) {
        val action =
            LoginFragmentDirections.actionLoginFragmentToBottomDialogFragment(errorMessage)
        findNavController().navigate(action)
    }


}