package com.example.halanchallenge.ui.adapters

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

@BindingAdapter("loadImage")
fun bindLoadImage(view: AppCompatImageView, url: String) {
    if (!url.isNullOrEmpty())
        Picasso.get().load(url).into(view)
}

@BindingAdapter("loadCircleImage")
fun bindLoadImage(view: CircleImageView, url: String) {
    if (!url.isNullOrEmpty())
        Picasso.get().load(url).into(view)
}