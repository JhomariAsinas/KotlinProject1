package com.example.jhomasinas.kotlinproject1

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.view.View

class AddProduct : AppCompatActivity() {

    private var txtName:     TextInputEditText? = null
    private var txtDes:      TextInputEditText? = null
    private var txtPrice:    TextInputEditText? = null
    private var txtQuantity: TextInputEditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product)

        txtName      = findViewById<View>(R.id.inputName) as TextInputEditText
        txtDes       = findViewById<View>(R.id.inputDes)  as TextInputEditText
        txtPrice     = findViewById<View>(R.id.inputPrice)  as TextInputEditText
        txtQuantity  = findViewById<View>(R.id.inputQuantity)  as TextInputEditText
    }
}
