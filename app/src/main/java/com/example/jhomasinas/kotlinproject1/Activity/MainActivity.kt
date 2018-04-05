package com.example.jhomasinas.kotlinproject1.Activity


import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.example.jhomasinas.kotlinproject1.*
import com.example.jhomasinas.kotlinproject1.Adapter.RecycleAdapter
import com.example.jhomasinas.kotlinproject1.Config.ProductAPI
import com.example.jhomasinas.kotlinproject1.Config.ProductResponse
import com.example.jhomasinas.kotlinproject1.Model.Product
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity(), RecycleAdapter.Delegate {

    private var recyclerView2 : RecyclerView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView2 = findViewById<View>(R.id.recyclerView) as RecyclerView
        recyclerView2!!.layoutManager = GridLayoutManager(this@MainActivity,2)
        getProduct()
        swipeLayout.setOnRefreshListener {
            getProduct()
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
                 val adapter = RecycleAdapter(product, this@MainActivity)
                 recyclerView2!!.adapter = adapter
                 swipeLayout.isRefreshing = false
             }

         })

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.addItem -> {
                val intent = Intent(applicationContext, CartActivity::class.java)
                startActivity(intent)
                return true

            }
            R.id.Settings ->{
                Toast.makeText(this,"Settings Selected",Toast.LENGTH_SHORT ).show()
                return true
            }
            else-> return super.onOptionsItemSelected(item)
        }
    }

    override fun onClickProduct(product: Product) {
        val intent = Intent(this@MainActivity, ProductDetail::class.java)
        intent.putExtra("Image",product.prodimage)
        intent.putExtra("Name",product.prodname)
        intent.putExtra("Description",product.prodescrip)
        intent.putExtra("Items",product.proditems)
        intent.putExtra("Price",product.prodprice)
        intent.putExtra("Code",product.prodcode)
        intent.putExtra("Category",product.prodcat)
        startActivity(intent)

    }




}

