package com.example.halanchallenge.ui.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.halanchallenge.R
import com.example.halanchallenge.databinding.ProductsListFragmentBinding
import com.example.halanchallenge.ui.MainActivity
import com.example.halanchallenge.ui.adapters.ProductClick
import com.example.halanchallenge.ui.adapters.ProductsAdapter
import com.example.halanchallenge.util.State
import com.example.halanchallenge.util.makeItInVisible
import com.example.halanchallenge.util.makeItVisible
import org.koin.android.viewmodel.ext.android.viewModel

class ProductsListFragment : Fragment() {

    private lateinit var binding: ProductsListFragmentBinding
    private val viewModel: ProductsListViewModel by viewModel()
    private var productsAdapter: ProductsAdapter? = null
    private val safeArgs : ProductsListFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ProductsListFragmentBinding.inflate(inflater)

        setupToolbar()
        setupAdapter()
        setupObservers()
        getProductsInfo(safeArgs.loginResponse.token)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            profile = safeArgs.loginResponse.profile
        }
    }

    private fun setupAdapter() {
        productsAdapter = ProductsAdapter(ProductClick {it ->
            findNavController().navigate(
                ProductsListFragmentDirections.actionProductsListFragmentToProductDetailsFragment(
                    it
                )
            )
        })
        // Sets the adapter of the RecyclerView
        binding.productsListRv.adapter = productsAdapter
        postponeEnterTransition()
        binding.productsListRv.viewTreeObserver.addOnPreDrawListener {
            startPostponedEnterTransition()
            true
        }
    }

    private fun setupToolbar(){
        if(requireActivity() is MainActivity){
            (activity as AppCompatActivity?)!!.setSupportActionBar(binding.listToolbar)
            (activity as AppCompatActivity?)!!.supportActionBar!!.setTitle(R.string.my_products)
        }
    }

    private fun setupObservers() {
        viewModel.productInfo.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is State.Loading -> binding.progress.makeItVisible()
                is State.Success -> {
                    if (state.data.products?.isNotEmpty()!!){
                        productsAdapter?.submitList(state.data.products)
                        binding.progress.makeItInVisible()
                    }
                    else
                        Toast.makeText(activity, "No data", Toast.LENGTH_SHORT).show()
                }
                is State.Error -> {
                    binding.progress.makeItInVisible()
                    Toast.makeText(activity, state.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    override fun onResume() {
        super.onResume()
        getProductsInfo(safeArgs.loginResponse.token)
    }

    private fun getProductsInfo(token: String?){
        viewModel.getProductsInfo(token)
    }

    // clear views references to fix memory leaks
    override fun onDestroyView() {
        super.onDestroyView()
        binding.unbind()
    }

}