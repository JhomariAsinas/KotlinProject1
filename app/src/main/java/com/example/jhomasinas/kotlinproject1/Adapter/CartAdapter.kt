package com.example.jhomasinas.kotlinproject1.Adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.jhomasinas.kotlinproject1.Model.Cart
import com.example.jhomasinas.kotlinproject1.R
import com.squareup.picasso.Picasso

/**
 * Created by JhomAsinas on 4/4/2018.
 */
class CartAdapter(val userlist: ArrayList<Cart>, val delegate : Delegate) : RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    interface Delegate {
        fun onClickProduct(cart: Cart)
        fun onTransact(cart: Cart)
    }

    override fun getItemCount(): Int {
        return userlist.size
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val cart: Cart = userlist[position]

        holder?.prodName2?.text   = cart.product_name
        holder?.prodAmount2?.text = "$"+cart.product_price+".00"
        holder?.prodItems2?.text  = cart.product_items+" Items Order"

        Picasso.get()
                .load("http://192.168.1.101/e-commerce/assets/image/" + cart.product_image)
                .resize(450, 450)
                .centerCrop()
                .into(holder?.prodImage2)
        if(cart.product_status.toString() == "active"){
            holder?.btntrans?.visibility  = View.INVISIBLE
            holder?.btnedit?.visibility   = View.INVISIBLE
            holder?.btndelete?.visibility = View.INVISIBLE
            holder?.hide?.visibility      = View.VISIBLE
        }else{
            holder?.btntrans?.setOnClickListener(object : View.OnClickListener {
                override fun onClick(p0: View?) {
                   delegate.onTransact(userlist.get(position))
                }
            })
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.list_cart, parent, false)
        return ViewHolder(v)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val prodName2    = itemView.findViewById<View>(R.id.cartName)     as TextView
        val prodImage2   = itemView.findViewById<View>(R.id.imageView)    as ImageView
        val prodAmount2  = itemView.findViewById<View>(R.id.cartAmount)   as TextView
        val prodItems2   = itemView.findViewById<View>(R.id.cartItems)    as TextView
        val btntrans     = itemView.findViewById<View>(R.id.btnTransact)  as TextView
        val btnedit      = itemView.findViewById<View>(R.id.btnEdit)      as TextView
        val btndelete    = itemView.findViewById<View>(R.id.btnDelete)    as TextView
        val hide         = itemView.findViewById<View>(R.id.notifProcess) as TextView
    }

}