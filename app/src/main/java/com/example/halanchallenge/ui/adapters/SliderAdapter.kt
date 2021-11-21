package com.example.halanchallenge.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.halanchallenge.R
import com.smarteist.autoimageslider.SliderViewAdapter
import com.squareup.picasso.Picasso

class SliderAdapter :
    SliderViewAdapter<SliderAdapter.SliderViewHolder>() {
    private var mSliderItems: List<String>? = null

    fun renewItems(sliderItems: List<String>?) {
        mSliderItems = sliderItems
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup): SliderViewHolder {
        val inflate: View = LayoutInflater.from(parent.context).inflate(R.layout.image_holder, null)
        return SliderViewHolder(inflate)
    }

    override fun onBindViewHolder(viewHolder: SliderViewHolder, position: Int) {
        //load image into view
        Picasso.get().load(mSliderItems?.get(position)).fit().into(viewHolder.imageView)
    }

    override fun getCount(): Int {
        return mSliderItems!!.size
    }

    inner class SliderViewHolder(itemView: View) : ViewHolder(itemView) {
        var imageView: ImageView = itemView.findViewById(R.id.imageSlider)
    }
}