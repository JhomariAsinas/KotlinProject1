package com.example.jhomasinas.kotlinproject1.Activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import com.example.jhomasinas.kotlinproject1.Config.ProductAPI
import com.example.jhomasinas.kotlinproject1.Config.ProductResponse
import com.example.jhomasinas.kotlinproject1.R
import com.example.jhomasinas.kotlinproject1.SharedPref
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_product_detail.*
import org.jetbrains.anko.*
import retrofit2.Call
import retrofit2.Callback


class ProductDetail : AppCompatActivity() {
    val productApiserve by lazy {
        ProductAPI.create()
    }

    var disposable : Disposable? = null

    var prodimg: String? = null
    var code: String? = null
    var items2: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)
        initViews()
        loadImage()
        btnAddtoCart.setOnClickListener {
           if(!SharedPref.getmInstance(this@ProductDetail).isUserData){
               createAccountdialog()
           }else{
               createDialog()
           }
        }

    }

    fun initViews(){
        code = intent.getStringExtra("Code")
        prodimg = intent.getStringExtra("Image")
        prodView.text = intent.getStringExtra("Name")
        categoryView.text = intent.getStringExtra("Category")
        itemView.text = intent.getStringExtra("Items") + " Items Available"
        priceView.text = intent.getStringExtra("Price")
        descripView.text = intent.getStringExtra("Description")
        items2 = intent.getStringExtra("Items").toInt()
    }

    fun loadImage(){
        val url: String = "http://192.168.1.101/e-commerce/assets/image/$prodimg"
        val imageView: ImageView = findViewById(R.id.prodImage)
        Picasso.get()
                .load(url)
                .resize(300, 450)
                .centerCrop()
                .into(imageView)
    }

    fun addtoCart(inputCode: String?,inputItems: Int,fullname: String){
        disposable = productApiserve.addtoCart(inputCode!!,inputItems,fullname)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {result->toast("Added to Cart Successfully")},
                        {error-> toast("Error $error")}
                )
    }

    fun createDialog(){
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_item, null)
        var inputItems = dialogView.findViewById<View>(R.id.itemsInput) as EditText

        val builder = AlertDialog.Builder(this)
                .setView(dialogView)
                .setPositiveButton("Proceed",null)
                .setNegativeButton("Cancel",null)
        val dialog = builder.show()

        dialog.show()

        dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener({
            dialog.dismiss()
        })
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener({
            val items: Int = inputItems.text.toString().toInt()
            if(items > items2!!){
                toast("Insufficient Items")
            }else{
                addtoCart(code,items,SharedPref.getmInstance(this@ProductDetail).customName!!)
            }
        })
    }

    fun createAccountdialog(){
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_account, null)
        val builder = AlertDialog.Builder(this)
                .setView(dialogView)
                .setPositiveButton("ADD ACCOUNT",null)
                .setNegativeButton("Cancel",null)
        val dialog = builder.show()

        dialog.show()

        dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener({
            dialog.dismiss()
        })

        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener({
                val intent = Intent(this@ProductDetail,CustomerActivity::class.java)
                startActivity(intent)
                dialog.dismiss()

        })
    }
}
