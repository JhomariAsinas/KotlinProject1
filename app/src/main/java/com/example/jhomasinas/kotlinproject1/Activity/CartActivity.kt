package com.example.jhomasinas.kotlinproject1.Activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.LinearLayout
import com.example.jhomasinas.kotlinproject1.*
import com.example.jhomasinas.kotlinproject1.Adapter.CartAdapter
import com.example.jhomasinas.kotlinproject1.Config.ProductAPI
import com.example.jhomasinas.kotlinproject1.Config.ProductResponse
import com.example.jhomasinas.kotlinproject1.Model.Cart
import com.example.jhomasinas.kotlinproject1.Model.Product
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CartActivity : AppCompatActivity(), CartAdapter.Delegate {

    override fun onTransact(cart: Cart) {
        val intent = Intent(this@CartActivity,TransactActivity::class.java)
        startActivity(intent)
        finish()

    }

    override fun onClickProduct(cart: Cart) {

    }

    var recyclerView2: RecyclerView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        recyclerView2 = findViewById<View>(R.id.recyclerView) as RecyclerView
        recyclerView2?.layoutManager = LinearLayoutManager(this,LinearLayout.VERTICAL,false)
        getCart()
    }

    fun getCart(){
        val productService = ProductAPI.create()
        val call = productService.getCart()

        call.enqueue(object: Callback<ProductResponse>{
            override fun onFailure(call: Call<ProductResponse>?, t: Throwable?) {
                toast("Network Error")
            }

            override fun onResponse(call: Call<ProductResponse>?, response: Response<ProductResponse>?) {
               val cart: ArrayList<Cart> = response!!.body()!!.cart2!!
                val adapter = CartAdapter(cart, this@CartActivity)
                recyclerView2!!.adapter = adapter
            }

        })
    }
}
