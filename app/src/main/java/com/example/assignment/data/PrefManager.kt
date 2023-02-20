package com.example.assignment.data

import android.content.Context
import android.content.SharedPreferences
import android.util.Log

class PrefManager(context: Context) {

    private val PRIVATE_MODE = 0
    private val EMAIL = "email"

    private val pref :SharedPreferences = context.getSharedPreferences(PREF_NAME,PRIVATE_MODE)
    val editor :SharedPreferences.Editor = pref.edit()

    fun setLogin(isLogin:Boolean){
        editor.putBoolean(IS_LOGIN,isLogin)
        editor.commit()
    }


    fun setEmail(username:String){
        editor.putString(EMAIL,username)
        Log.d("145",username)
    }

    fun setData(firstName:String,lastName:String,phoneNumber:String,){
//        editor.putString(EMAIL,email)
        editor.putString(FIRST_NAME,firstName)
        editor.putString(LAST_NAME,lastName)
        editor.putString(PHONE_NUMBER,phoneNumber)
//        Log.d("145",username)
    }
    fun setPrice(sum:Int){
        editor.putInt(TOTALPRICE,sum)
        editor.commit()
    }
    fun getPrice(): Int {
        return pref.getInt(TOTALPRICE,0)
    }

    fun setPriceDollar(sum:Double){
        editor.putFloat(TOTAL_PRICE_DOLLAR,sum.toFloat())
        editor.commit()
    }
    fun getPriceDollar(): Float {
        return pref.getFloat(TOTAL_PRICE_DOLLAR,0.0.toFloat())
    }
    fun setPassword(password:String){
        editor.putString(PASSWORD,password)
    }

    fun isLogin() : Boolean {
        return pref.getBoolean(IS_LOGIN,false)
    }
    fun isSuccessPurchased() : Boolean {
        return pref.getBoolean(IS_SUCCESS_PURCHASED,false)
    }

    fun setSuccessPurchased(isSuccessPurchased :Boolean){
        editor.putBoolean(IS_SUCCESS_PURCHASED,isSuccessPurchased)
        editor.commit()
    }
    fun getEmail(): String? {
        return pref.getString(EMAIL,"null")
    }
    fun getFirstName(): String? {
        return pref.getString(FIRST_NAME,"Name")
    }
    fun getLastName(): String? {
        return pref.getString(LAST_NAME,"null")
    }
    fun getPhoneNumber(): String? {
        return pref.getString(PHONE_NUMBER,"null")
    }


    fun removeData(){
        editor.clear()
        editor.commit()
    }

    companion object{
        private val PREF_NAME = "sharedPreference"
        private val IS_LOGIN = "is_login"
        private val IS_SUCCESS_PURCHASED = "is_success_purchased"

        private val FIRST_NAME = "first_name"
        private val LAST_NAME = "last_name"
        private val PHONE_NUMBER = "phone_number"
        private val TOTALPRICE = "price"
        private val TOTAL_PRICE_DOLLAR = "dollarprice"
        const val PASSWORD = "pass"

    }
}