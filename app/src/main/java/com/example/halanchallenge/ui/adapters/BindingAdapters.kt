package com.example.halanchallenge.ui.adapters

import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

@BindingAdapter("loadImage")
fun bindLoadImage(view: CircleImageView, url: String) {
    if (!url.isNullOrEmpty())
        Picasso.get().load(url).into(view)
}