package com.example.jhomasinas.kotlinproject1

import com.google.gson.annotations.SerializedName

/**
 * Created by JhomAsinas on 3/21/2018.
 */

class Product {

    @SerializedName("product_name")
    var prodname : String? = null

    @SerializedName("product_code")
    var prodcode : String? = null

    @SerializedName("product_des")
    var prodescrip : String? = null

}