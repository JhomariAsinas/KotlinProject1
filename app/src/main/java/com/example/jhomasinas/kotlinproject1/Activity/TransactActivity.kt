package com.example.jhomasinas.kotlinproject1.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.jhomasinas.kotlinproject1.R

class TransactActivity : AppCompatActivity() {
    private var code: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transact)
        code = intent.getStringExtra("Code")
    }


}
