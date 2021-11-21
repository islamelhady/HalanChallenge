package com.example.halanchallenge.ui.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.halanchallenge.databinding.ProductsListFragmentBinding
import com.example.halanchallenge.ui.adapters.ProductsAdapter
import com.example.halanchallenge.util.State
import org.koin.android.viewmodel.ext.android.viewModel

class ProductsListFragment : Fragment() {

    private lateinit var binding: ProductsListFragmentBinding
    private val viewModel: ProductsListViewModel by viewModel()
    private var productsAdapter: ProductsAdapter? = null
    private var accessToken: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ProductsListFragmentBinding.inflate(inflater)

        setupAdapter()
        setupObservers()
        accessToken = arguments?.getString("TOKEN")
        getProductsInfo("Bearer $accessToken")

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            profile = arguments?.getParcelable("PROFILE")
        }
    }

    private fun setupAdapter() {
        productsAdapter = ProductsAdapter()

        // Sets the adapter of the RecyclerView
        binding.productsListRv.adapter = productsAdapter
        postponeEnterTransition()
        binding.productsListRv.viewTreeObserver.addOnPreDrawListener {
            startPostponedEnterTransition()
            true
        }
    }

    private fun setupObservers() {
        viewModel.productInfo.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is State.Success -> {
                    if (state.data.products?.isNotEmpty()!!)
                        productsAdapter?.submitList(state.data.products)
                    else
                        Toast.makeText(activity, "No data", Toast.LENGTH_SHORT).show()
                }
                is State.Error -> {
                    Toast.makeText(activity, state.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    override fun onResume() {
        super.onResume()
        getProductsInfo(accessToken)
    }

    private fun getProductsInfo(token: String?){
        viewModel.getProductsInfo(token)
    }

}