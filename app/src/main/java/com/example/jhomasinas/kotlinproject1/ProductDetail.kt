package com.example.jhomasinas.kotlinproject1

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_product_detail.*
import org.jetbrains.anko.*
import retrofit2.Call
import retrofit2.Callback


class ProductDetail : AppCompatActivity() {
    var prodimg: String? = null
    var code: String? = null
    var items2: Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)
        code = intent.getStringExtra("Code")
        prodimg = intent.getStringExtra("Image")
        prodView.text = intent.getStringExtra("Name")
        categoryView.text = intent.getStringExtra("Category")
        itemView.text = intent.getStringExtra("Items") + " Items Available"
        priceView.text = intent.getStringExtra("Price")
        descripView.text = intent.getStringExtra("Description")
        items2 = intent.getStringExtra("Items").toInt()
        loadImage()
        btnAddtoCart.setOnClickListener {
           createDialog()
        }

    }

    fun loadImage(){
        val url: String = "http://192.168.15.74/e-commerce/assets/image/$prodimg"
        val imageView: ImageView = findViewById(R.id.prodImage)
        Picasso.get()
                .load(url)
                .resize(300, 450)
                .centerCrop()
                .into(imageView)
    }

    fun addtoCart(inputCode: String?,inputItems: Int){
        val productService = ProductAPI.create()
        val call = productService.addtoCart(inputCode!!,inputItems)
        call.enqueue(object : Callback<ProductResponse> {
            override fun onFailure(call: Call<ProductResponse>?, t: Throwable?) {
                toast("Network Error")
            }

            override fun onResponse(call: Call<ProductResponse>?, response:  retrofit2.Response<ProductResponse>?) {
                val res: Boolean = response!!.body()!!.response!!
                if(res){
                    toast("Items Added SuccessFully")
                }else{
                    toast("Items Added UnSuccessFully")
                }
            }

        })
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
                addtoCart(code,items)
            }
        })
    }
}
