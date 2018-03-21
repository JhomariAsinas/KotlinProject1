package com.example.jhomasinas.kotlinproject1


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {
    private var recyclerView2 : RecyclerView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView2 = findViewById<View>(R.id.recyclerView) as RecyclerView
        recyclerView2!!.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL , false)

        btnLoad.setOnClickListener{
            getProduct();
        }
    }

     fun getProduct(){
        val productService = ProductAPI.create()
        val call = productService.getProduct()

         call.enqueue(object : Callback<ProductResponse>{
             override fun onFailure(call: Call<ProductResponse>?, t: Throwable?) {
                 Toast.makeText(this@MainActivity, "Error", Toast.LENGTH_SHORT).show()
             }

             override fun onResponse(call: Call<ProductResponse>?, response:  retrofit2.Response<ProductResponse>?) {
                 var product: ArrayList<Product> = response!!.body()!!.product2!!
                 val adapter = RecycleAdapter(product)
                 recyclerView2!!.adapter = adapter
             }

         })

    }
}
