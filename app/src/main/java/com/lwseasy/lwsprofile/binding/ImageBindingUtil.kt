package com.lwseasy.lwsprofile.binding

import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.lwseasy.lwsprofile.R
import java.io.File

@BindingAdapter("imageUrlBind")
fun imageUrlBind(image: ImageView, url: String?) {
    if (url != null) {
        Glide.with(image.context).load(url).into(image)
    }
}

@BindingAdapter("imageUrlRoundBind")
fun imageUrlRoundBind(image: ImageView, url: String?) {
    Glide.with(image.context).load(R.drawable.profile_default)
        .apply(RequestOptions.circleCropTransform()).into(image)
}