package com.example.jhomasinas.kotlinproject1.Config

import com.example.jhomasinas.kotlinproject1.Model.Cart
import com.example.jhomasinas.kotlinproject1.Model.Product
import com.google.gson.annotations.SerializedName
import kotlin.collections.ArrayList

/**
 * Created by JhomAsinas on 3/21/2018.
 */
class ProductResponse {

    @SerializedName("product")
    var product2: ArrayList<Product>? = null

    @SerializedName("cart")
    var cart2: ArrayList<Cart>? = null

    @SerializedName("response")
    var response: Boolean? = null

    
}