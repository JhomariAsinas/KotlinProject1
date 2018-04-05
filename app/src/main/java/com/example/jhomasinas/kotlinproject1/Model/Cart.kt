package com.example.jhomasinas.kotlinproject1.Model

import com.google.gson.annotations.SerializedName

/**
 * Created by JhomAsinas on 4/4/2018.
 */
class  Cart{

    @SerializedName("product_name")
    var prodname : String? = null

    @SerializedName("product_price")
    var prodprice : String? = null

    @SerializedName("product_items")
    var proditems : String? = null

    @SerializedName("product_image")
    var prodimage : String? = null

    @SerializedName("product_status")
    var prodstatus : String? = null

    @SerializedName("product_code")
    var prodcode : String? = null

}