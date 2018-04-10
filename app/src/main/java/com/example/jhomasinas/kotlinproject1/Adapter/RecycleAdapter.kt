package com.example.jhomasinas.kotlinproject1.Adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.example.jhomasinas.kotlinproject1.Model.Product
import com.example.jhomasinas.kotlinproject1.R
import com.squareup.picasso.Picasso

/**
 * Created by JhomAsinas on 3/21/2018.
 */
class RecycleAdapter(val userlist: ArrayList<Product>, val delegate : Delegate) : RecyclerView.Adapter<RecycleAdapter.ViewHolder>() {

    interface Delegate {
        fun onClickProduct(product: Product)

    }

    override fun getItemCount(): Int {
       return userlist.size
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val product: Product = userlist[position]

        holder?.prodName?.text  = product.product_name
        Picasso.get()
                .load("http://192.168.1.101/e-commerce/assets/image/"+product.product_image)
                .resize(450, 450)
                .centerCrop()
                .into(holder?.prodImage)

        holder?.product_container?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                delegate.onClickProduct(userlist.get(position))
            }
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(v)
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val prodName          = itemView.findViewById<View>(R.id.prodName)        as TextView
        val prodImage         = itemView.findViewById<View>(R.id.prodImgView)     as ImageView
        val product_container = itemView.findViewById<View>(R.id.containerlayout) as RelativeLayout
    }

}