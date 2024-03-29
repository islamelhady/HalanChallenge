package com.example.halanchallenge.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.halanchallenge.R
import com.example.halanchallenge.databinding.ProductDetailsFragmentBinding
import com.example.halanchallenge.ui.adapters.SliderAdapter
import com.smarteist.autoimageslider.SliderView

class ProductDetailsFragment : Fragment() {

    private lateinit var binding: ProductDetailsFragmentBinding
    private val args: ProductDetailsFragmentArgs by navArgs()
    private var imageList: List<String>? = null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ProductDetailsFragmentBinding.inflate(inflater)

        imageList = args.products.images


        setImageInSlider(imageList, binding.productImagesBanner)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            product = args.products
            backClick = this@ProductDetailsFragment
        }
    }

    /**
     * Navigate to the productsList.
     */
    fun goToListProducts(){
        binding.back.setOnClickListener {
            findNavController().navigate(R.id.action_productDetailsFragment_to_productsListFragment)
        }
    }

    // clear views references to fix memory leaks
    override fun onDestroyView() {
        super.onDestroyView()
        binding.unbind()
    }

}

private fun setImageInSlider(images: List<String>?, imageSlider: SliderView) {
    val adapter = SliderAdapter()
    adapter.renewItems(images)
    imageSlider.setSliderAdapter(adapter)
    imageSlider.isAutoCycle = true
    imageSlider.startAutoCycle()
}