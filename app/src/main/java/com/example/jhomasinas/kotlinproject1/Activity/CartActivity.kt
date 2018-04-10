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
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CartActivity : AppCompatActivity(), CartAdapter.Delegate {
    val productApiserve by lazy {
        ProductAPI.create()
    }

    var disposable : Disposable? = null

    var recyclerView2: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        if(!SharedPref.getmInstance(this@CartActivity).isUserData){
            startActivity(Intent(this@CartActivity,CustomerActivity::class.java))
            finish()
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        recyclerView2 = findViewById<View>(R.id.recyclerView) as RecyclerView
        recyclerView2?.layoutManager = LinearLayoutManager(this,LinearLayout.VERTICAL,false)
        getCart()
    }

    fun getCart(){
        disposable = productApiserve.getCart()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {result -> handleResponse(result)},
                        {error  -> toast("Error ${error.localizedMessage}")}
                )
    }

    fun handleResponse(cart: List<Cart>){
        val mCart: ArrayList<Cart> = ArrayList(cart)
        val adapter = CartAdapter(mCart,this)
        recyclerView2!!.adapter = adapter
    }

    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }

    override fun onTransact(cart: Cart){
        val intent = Intent(this@CartActivity,PaymentActivity::class.java)
        intent.putExtra("Code",cart.product_code)
        startActivity(intent)
    }

    override fun onClickProduct(cart: Cart) {

    }
}
