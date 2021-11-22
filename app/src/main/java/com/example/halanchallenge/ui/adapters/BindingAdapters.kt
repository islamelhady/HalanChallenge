package com.example.halanchallenge.ui.adapters

import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView

@BindingAdapter("loadImage")
fun bindLoadImage(view: CircleImageView, url: String) {
    if(!url.isNullOrEmpty())
        Glide.with(view.context)
            .load(url)
            .into(view)
}