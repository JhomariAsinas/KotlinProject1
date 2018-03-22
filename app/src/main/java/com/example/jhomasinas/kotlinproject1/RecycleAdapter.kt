package com.example.jhomasinas.kotlinproject1

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView

/**
 * Created by JhomAsinas on 3/21/2018.
 */
class RecycleAdapter(val userlist: ArrayList<Product>, val delegate : RecycleAdapter.Delegate) : RecyclerView.Adapter<RecycleAdapter.ViewHolder>() {

    interface Delegate {
        fun onClickProduct(product: Product)
    }

    override fun getItemCount(): Int {
       return userlist.size
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val product: Product = userlist!![position]

        holder?.prodName?.text  = product.prodname
        holder?.prodCode?.text  = product.prodcode
        holder?.prodDes?.text   = product.prodescrip
        holder?.prodPrice?.text = "$"+product.prodprice+".00"

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
        val prodName  = itemView.findViewById<View>(R.id.prodName)  as TextView
        val prodCode  = itemView.findViewById<View>(R.id.prodCode)  as TextView
        val prodDes   = itemView.findViewById<View>(R.id.prodDes)   as TextView
        val prodPrice = itemView.findViewById<View>(R.id.prodPrice) as TextView
        val product_container = itemView.findViewById<LinearLayout>(R.id.product_container)
    }

}