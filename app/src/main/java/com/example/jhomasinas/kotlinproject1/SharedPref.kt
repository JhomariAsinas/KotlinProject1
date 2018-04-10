package com.example.jhomasinas.kotlinproject1

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by villaluna on 11/3/2017.
 */

class SharedPref private constructor(context: Context) {

    val isUserData: Boolean
        get() {
            val sharedPreferences = con.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
            if(sharedPreferences.getString(KEY_FULLNAME, null) != null){
                return true
            }else{
                return false
            }
        }

    val customName: String?
        get() {
            val sharedPreferences = con.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
            return sharedPreferences.getString(KEY_FULLNAME, null)
        }

    val customAddress: ?
        get() {
            val sharedPreferences = con.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
            return sharedPreferences.getString(KEY_ADDRESS, null)
        }

    val customContact: String?
        get() {
            val sharedPreferences = con.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
            return sharedPreferences.getString(KEY_CONTACT, null)
        }

    init {
        con = context
    }

    fun userData(fullname: String, address: String, contact: String): Boolean {

        val sharedPreferences = con.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val edit = sharedPreferences.edit()

        edit.putString(KEY_CONTACT, contact)
        edit.putString(KEY_ADDRESS, address)
        edit.putString(KEY_FULLNAME, fullname)

        edit.apply()

        return true
    }


    companion object {
        private var mInstance: SharedPref? = null
        private lateinit var con: Context

        private val SHARED_PREF_NAME = "sharedpref1212"
        private val KEY_CONTACT = "contact"
        private val KEY_ADDRESS = "address"
        private val KEY_FULLNAME = "fname"


        @Synchronized
        fun getmInstance(context: Context): SharedPref {
            if (mInstance == null) {
                mInstance = SharedPref(context)
            }
            return mInstance as SharedPref
        }
    }

}
