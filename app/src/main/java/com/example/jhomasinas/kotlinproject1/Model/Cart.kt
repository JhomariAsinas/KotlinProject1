package com.example.jhomasinas.kotlinproject1.Model

import com.google.gson.annotations.SerializedName

/**
 * Created by JhomAsinas on 4/4/2018.
 */

data class Cart(
        var product_name:  String,
        var product_price: String,
        var product_items: String,
        var product_image: String,
        var product_status: String,
        var product_code: String
)
