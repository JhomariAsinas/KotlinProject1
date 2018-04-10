package com.example.jhomasinas.kotlinproject1.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.jhomasinas.kotlinproject1.*
import kotlinx.android.synthetic.main.fragment_transact.*
import org.jetbrains.anko.toast

class CustomerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_transact)
        val name:    String = customerName.text.toString()
        val address: String = customerStreet.text.toString()+customerState.text.toString()
        val contact: String = customerNumber.text.toString()

        saveCustomer.setOnClickListener {
            if(SharedPref.getmInstance(this@CustomerActivity).userData(name,address,contact)){
                toast("Your information was successfully saved")
            }else{
                toast("Your information was unsuccessfully saved")
            }
        }
    }
}
