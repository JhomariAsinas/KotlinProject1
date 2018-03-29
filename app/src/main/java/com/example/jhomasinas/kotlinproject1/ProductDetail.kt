package com.example.jhomasinas.kotlinproject1

import android.media.Image
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso


class ProductDetail : AppCompatActivity() {
    var prodimg: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)
        prodimg = intent.getStringExtra("Image")
        loadImage()

    }

    fun loadImage(){
        val url: String = "http://192.168.15.74/e-commerce/assets/image/$prodimg"
        val imageView: ImageView = findViewById(R.id.prodImage)
        Picasso.get()
                .load(url)
                .resize(300, 450)
                .centerCrop()
                .into(imageView)
    }
}
