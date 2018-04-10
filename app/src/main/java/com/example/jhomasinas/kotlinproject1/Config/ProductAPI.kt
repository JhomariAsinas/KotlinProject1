package com.example.jhomasinas.kotlinproject1.Config



import com.example.jhomasinas.kotlinproject1.Model.Cart
import com.example.jhomasinas.kotlinproject1.Model.Product
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST


interface ProductAPI {

    @GET("admin/showSelprod/All")
    abstract fun getProduct(): Observable<List<Product>>

    @GET("admin/getCartdata")
    abstract fun getCart(): Observable<List<Cart>>

    @FormUrlEncoded
    @POST("admin/addtoCart")
    abstract fun addtoCart(@Field("code")code: String,
                           @Field("items")items: Int,
                           @Field("name")fname: String): Observable<ProductResponse>

    @FormUrlEncoded
    @POST("admin/transactProd")
    abstract fun transact(@Field("code")code:String,
                          @Field("payment")payment:String,
                          @Field("address")address:String,
                          @Field("name")fname:String,
                          @Field("contact")contact:String): Observable<ProductResponse>


    companion object {
        val BASE_URL = "http://192.168.1.101/mobilecom/"
        fun create(): ProductAPI {
            val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            return retrofit.create(ProductAPI::class.java)
        }
    }
}