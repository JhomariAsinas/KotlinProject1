package com.example.jhomasinas.kotlinproject1.Activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioGroup
import android.widget.Toast
import com.example.jhomasinas.kotlinproject1.*
import com.example.jhomasinas.kotlinproject1.Config.ProductAPI
import com.example.jhomasinas.kotlinproject1.Config.ProductResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_blank.*
import org.jetbrains.anko.radioGroup
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PaymentActivity : AppCompatActivity() {
    val productApiserve by lazy {
        ProductAPI.create()
    }

    var disposable : Disposable? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_blank)
        val code2:String = intent.getStringExtra("Code")
        button.setOnClickListener {
            transactProd(code2)
        }
    }

    fun transactProd(code1: String){
        var s2: String? = null

        if(radCash.isChecked){
            s2 = "Cash on Delivery"
        }else if (radRemit.isChecked){
            s2 = "Remittance"
        }else{
            toast("You must select your mode of payment")
            return
        }

        disposable = productApiserve.transact(code1,s2,
                SharedPref.getmInstance(this).customAddress!!,
                SharedPref.getmInstance(this).customName!!,
                SharedPref.getmInstance(this).customContact!!)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {result->toast("Transaction was successfull")},
                        {error-> toast("Error")}
                )
    }

    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }
}
