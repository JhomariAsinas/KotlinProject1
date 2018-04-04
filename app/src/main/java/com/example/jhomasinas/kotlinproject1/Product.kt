package com.example.jhomasinas.kotlinproject1

import com.google.gson.annotations.SerializedName

class  Product {

    @SerializedName("product_name")
    var prodname : String? = null

    @SerializedName("product_code")
    var prodcode : String? = null

    @SerializedName("product_des")
    var prodescrip : String? = null

    @SerializedName("product_price")
    var prodprice : String? = null

    @SerializedName("product_items")
    var proditems : String? = null

    @SerializedName("product_image")
    var prodimage : String? = null

    @SerializedName("product_category")
    var prodcat : String? = null

}