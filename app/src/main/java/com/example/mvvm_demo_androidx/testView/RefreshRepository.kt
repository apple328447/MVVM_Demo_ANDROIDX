package com.example.mvvm_demo_androidx.testView

import android.os.Handler


/**
 * Model：管理資料來源如API和本地資料庫
 * */
class RefreshRepository {
    fun retrieveData(callback: onDataReadyCallback) {
        Handler().postDelayed({
            callback.onDataReady(updateText(), false) //回傳
        }, 500)
    }

    //Interface實作Call Back機制
    //http://andy02172001.blogspot.com/2017/10/androidinterfacecall-back.html
    interface onDataReadyCallback {
        fun onDataReady(data: String?, loading: Boolean?)
    }

    private fun updateText(): String? {
        val randomNumber = (Math.random() * 10).toInt()
        return Integer.toString(randomNumber)
    }
}