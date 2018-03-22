package com.example.jhomasinas.kotlinproject1



import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST


interface ProductAPI {

    @GET("admin/showSelprod/All")
    abstract fun getProduct(): Call<ProductResponse>

    @POST("admin/insertProd")
    @FormUrlEncoded
    abstract fun insertProduct(@Field("prodName") prodName: String,
                               @Field("prodDes")  prodDes:  String,
                               @Field("price")    price:    Int,
                               @Field("quantity") prodNum:  Int
                               ) : Call<Product>

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