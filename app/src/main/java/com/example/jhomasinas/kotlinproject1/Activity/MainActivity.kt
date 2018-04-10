package com.example.jhomasinas.kotlinproject1.Activity


import android.app.ProgressDialog
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
import com.example.jhomasinas.kotlinproject1.Model.Product
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity(), RecycleAdapter.Delegate {
    val productApiserve by lazy {
        ProductAPI.create()
    }

    var disposable : Disposable? = null
    var mProductList: ArrayList<Product>? = null

    private var recyclerView2 : RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView2 = findViewById<View>(R.id.recyclerView) as RecyclerView
        recyclerView2!!.setHasFixedSize(true)
        recyclerView2!!.layoutManager = GridLayoutManager(this@MainActivity,2)

        getProduct()

        swipeLayout.setOnRefreshListener {
            getProduct()
        }
    }


    fun getProduct(){
         disposable = productApiserve.getProduct()
                 .subscribeOn(Schedulers.io())
                 .observeOn(AndroidSchedulers.mainThread())
                 .subscribe(
                         {result-> handleResponse(result)},
                         {error-> toast("Error ${error.localizedMessage}")}
                 )
    }
    fun handleResponse(prodList: List<Product>){
        mProductList = ArrayList(prodList)
        val adapter = RecycleAdapter(mProductList!!,this)
        recyclerView2!!.adapter = adapter
    }

    override fun onPause() {
        super.onPause()
        disposable?.dispose()
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
        intent.putExtra("Image",product.product_image)
        intent.putExtra("Name",product.product_name)
        intent.putExtra("Description",product.product_des)
        intent.putExtra("Items",product.product_items)
        intent.putExtra("Price",product.product_price)
        intent.putExtra("Code",product.product_code)
        intent.putExtra("Category",product.product_category)
        startActivity(intent)

    }




}

