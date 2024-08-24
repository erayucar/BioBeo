package com.erayucar.biobeo.utils

import android.content.Context

object Utils {
    fun isLogin(context: Context): Boolean {
        val sharedPref = context.getSharedPreferences("my_shared_pref", Context.MODE_PRIVATE)
        return sharedPref.getString("token", "")?.isNotEmpty() == true
    }


    fun logOut(context: Context) {
        val sharedPref = context.getSharedPreferences("my_shared_pref", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()

        editor.putString("token", "")
        editor.apply()

    }
    fun saveToken(context: Context, token: String) {
        val sharedPref = context.getSharedPreferences("my_shared_pref", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()

        editor.putString("token", "Bearer $token")
        editor.apply()

    }
    fun getToken(context: Context): String {
        val sharedPref = context.getSharedPreferences("my_shared_pref", Context.MODE_PRIVATE)

        return sharedPref.getString("token", "").toString()
    }

}