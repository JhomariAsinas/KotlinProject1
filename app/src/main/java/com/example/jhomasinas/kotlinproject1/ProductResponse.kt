package com.example.jhomasinas.kotlinproject1

import com.google.gson.annotations.SerializedName
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by JhomAsinas on 3/21/2018.
 */
class ProductResponse {

    @SerializedName("product")
    var product2: ArrayList<Product>? = null

    @SerializedName("response")
    var response: Boolean? = null

    
}