package com.example.jhomasinas.kotlinproject1



import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


/**
 * Created by JhomAsinas on 3/21/2018.
 */
 interface ProductAPI {

    @GET("admin/showSelprod/All")
    abstract fun getProduct(): Call<ProductResponse>

    companion object {
        val BASE_URL = "http://192.168.1.95/mobilecom/"
        fun create(): ProductAPI {
            val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            return retrofit.create(ProductAPI::class.java)
        }
    }
}